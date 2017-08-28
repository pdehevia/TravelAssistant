package proyect.travelassistant.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.ResultActivity;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.beans.ScheduledInfoBean;
import proyect.travelassistant.beans.worldweather.HourlyBean;
import proyect.travelassistant.beans.worldweather.WeatherBean;
import proyect.travelassistant.sqlite.Consult;
import proyect.travelassistant.sqlite.ConsultsDB;
import proyect.travelassistant.sqlite.CriteryDB;
import proyect.travelassistant.sqlite.NotifForConsult;
import proyect.travelassistant.sqlite.NotifForConsultDB;
import proyect.travelassistant.sqlite.Recom;
import proyect.travelassistant.sqlite.RecomsForConsult;
import proyect.travelassistant.sqlite.RecomsForConsultDB;
import proyect.travelassistant.sqlite.RecomsDB;

public class FragmentTabResultsRecom extends Fragment {

    private LinearLayout ll;
    private long idConsulta;
    private boolean guardado;
    private Map<Long, Long> indexRow = new HashMap<>();
    private Map<Long,Boolean> checkBoxIndex = new HashMap<>();
    private ResultActivity ra;
    private List<Long> recomsExists = new ArrayList<>();
    private List<String> recomsDescriptions = new ArrayList<>();
    private Map<Long,Long> recomsRowsExists = new HashMap<>();
    private Map<Long,Boolean> recomsCheckExists = new HashMap<>();
    private List<Long>  drawRecomsExists = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_resultados_recomendaciones, container, false);

        ll = (LinearLayout) view.findViewById(R.id.linearLayoutRecoms);
        ra = (ResultActivity) getActivity();

        recomsExists.clear();
        recomsDescriptions.clear();
        recomsRowsExists.clear();
        recomsCheckExists.clear();
        drawRecomsExists.clear();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());

        //HEADER
        LinearLayout fileTitle = new LinearLayout(getContext());
        fileTitle.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams paramsF= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsF.setMargins(20,0,0,20);
        fileTitle.setGravity(Gravity.CENTER_VERTICAL);
        fileTitle.setLayoutParams(paramsF);
        fileTitle.setWeightSum(10);

        TextView tvHeader = new TextView(getContext());
        tvHeader.setText(ra.destino + " ("+ currentDateandTime+")");
        TextViewCompat.setTextAppearance(tvHeader, R.style.Style_Title_Historic);
        LinearLayout.LayoutParams par1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,8);
        par1.setMargins(10,10,30,10);
        tvHeader.setLayoutParams(par1);
        fileTitle.addView(tvHeader);

        LinearLayout llbtn = new LinearLayout(getContext());
        llbtn.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pllbtn= new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2);
        llbtn.setGravity(Gravity.CENTER);
        llbtn.setLayoutParams(pllbtn);

        Button btnNotifications = new Button(getContext());
        LinearLayout.LayoutParams btnParams2 = new LinearLayout.LayoutParams(100, 100);
        btnParams2.setMargins(10,10,10,10);
        btnNotifications.setLayoutParams(btnParams2);
        btnNotifications.setBackgroundResource(R.drawable.custom_btn_notifhist);
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotifForConsultDB nfcDB = new NotifForConsultDB(getContext());
                nfcDB.open();
                NotifForConsult nfc = nfcDB.getNotificacionParaConsultaId(idConsulta);
                nfcDB.close();

                ScheduledInfoBean scheduledInfo = new ScheduledInfoBean();
                scheduledInfo.setNameCity(ra.destino);
                scheduledInfo.setNfc(nfc);
                scheduledInfo.setRecoms(recomsDescriptions);
                AuxiliarData.getSingletonInstance().setScheduledInfo(scheduledInfo);

                FragmentScheduleNotification fsn = new FragmentScheduleNotification();
                fsn.show(getFragmentManager(),"ScheduleFragment");

            }
        });
        llbtn.addView(btnNotifications);

        fileTitle.addView(llbtn);
        ll.addView(fileTitle);
        /////////////////

        if(!guardado){
            indexRow.clear();
            checkBoxIndex.clear();
            //Crear consulta para guardar en el historial (Se guardarán máximo 10)
            ConsultsDB consultsDB = new ConsultsDB(getContext());
            consultsDB.open();

            if(ra.consultaExistente){
                idConsulta = ra.idConsulta;
                consultsDB.updateConsultaDate(idConsulta,currentDateandTime);
            }else{
                List<Consult> consults = consultsDB.getConsultas();
                if(consults.size()>9){
                    consultsDB.deleteConsulta(consults.get(0).getId());
                    RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
                    recomsForConsultDB.open();
                    recomsForConsultDB.deleteRecomendacionParaConsultaConIdConsulta(consults.get(0).getId());
                    recomsForConsultDB.close();
                }
                idConsulta = consultsDB.createConsulta(currentDateandTime,ra.destino,0,"","","",ra.lat,ra.lon);

                NotifForConsultDB nfcDB = new NotifForConsultDB(getContext());
                nfcDB.open();
                Long idNotif = 100 + idConsulta;
                nfcDB.createNotificacionParaConsulta(idConsulta,idNotif, "-", "-", "-", false, NotifForConsult.NO_ACTIVE_TYPE);
                nfcDB.close();
            }
            consultsDB.close();
        }

        //Recomendaciones básicas
        pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_0),0,true);

        //Recomendaciones por duración
        boolean semana = false;
        if(ra.criterio1.equals(getResources().getStringArray(R.array.days_array)[0])){
            //1 a 3 días
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_1),1,true);
        }else{
            //4 a 7 días
            semana = true;
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_2),2,true);
        }

        //Recomendaciones por motivo
        if(ra.criterio2.equals(getResources().getStringArray(R.array.reason_array)[0])){
            //Ocio
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_3),3,true);
        }else{
            //Trabajo
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_4),4,true);
        }

        //Recomendaciones por alojamientos
        if(ra.criterio3.equals(getResources().getStringArray(R.array.house_array)[0])){
            //Hotel/Hostal
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_5),5,true);

        }else if(ra.criterio3.equals(getResources().getStringArray(R.array.house_array)[1])) {
            //Casa en alquiler
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_6),6,true);
        } else{
            //Casa propia
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_7),7,true);
        }

        //Recomendaciones por transporte
        if(ra.criterio4.equals(getResources().getStringArray(R.array.transport_array)[0])){
            //Avión, barco, tren, autobús
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_12),12,true);
        }else{
            //Vehículo propio
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_11),11,true);
        }

        //Recomendaciones por temperatura

        TextView tvTitleTemp = new TextView(getContext());
        tvTitleTemp.setText(getResources().getString(R.string.title_weather));
        TextViewCompat.setTextAppearance(tvTitleTemp, R.style.Style_Recoms_Title);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,0,10,0);
        tvTitleTemp.setLayoutParams(params);
        ll.addView(tvTitleTemp);

        boolean llueve = false;
        boolean frio = false;
        boolean calor = false;

        String auxDesc = "";

        List<WeatherBean> weathers = ra.response.getData().getWeather();

        double tMin=200;
        double tMax=-200;

        for(int i=0;i<weathers.size();i++){

            String fecha = weathers.get(i).getDate();
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date aux = input.parse(fecha);
                fecha = output.format(aux);

            } catch (ParseException e) {}


            TextView tvTitle = new TextView(getContext());
            tvTitle.setText(fecha);

            auxDesc = auxDesc + fecha + "\n";

            TextViewCompat.setTextAppearance(tvTitle, R.style.Style_Recoms_Title);
            LinearLayout.LayoutParams par = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            par.setMargins(10,0,0,0);
            tvTitle.setLayoutParams(par);
            ll.addView(tvTitle);

            List<HourlyBean> hourlys = weathers.get(i).getHourly();

            boolean check1 = false;
            boolean check2 = false;
            boolean check3 = false;

            for(int j=0;j<hourlys.size();j++){
                double temp = Double.parseDouble(hourlys.get(j).getTempC());

                if(temp<tMin){
                    tMin = temp;
                }

                if(temp>tMax){
                    tMax =temp;
                }

                int codeRecom = clasificaCodigoClima(hourlys.get(j).getWeatherCode());
                if(codeRecom==8 && !check1){
                    llueve = true;
                    check1 = true;
                }
                if(temp>25 && !check2){
                    calor = true;
                    check2 = true;
                }else{
                    if(temp<16 && !check3){
                        frio = true;
                        check3 = true;
                    }
                }

                if(check1 && check2 && check3){
                    break;
                }
            }

            if(check1){
                insertaTextoTiempo(" - "+getResources().getString(R.string.title_recom_weather_rain));
                auxDesc = auxDesc + " - "+getResources().getString(R.string.title_recom_weather_rain) + "\n";
            }

            if(check2){
                insertaTextoTiempo(" - "+getResources().getString(R.string.title_recom_weather_hot));
                auxDesc = auxDesc + " - "+getResources().getString(R.string.title_recom_weather_hot) + "\n";
            }

            if(check3){
                insertaTextoTiempo(" - "+getResources().getString(R.string.title_recom_weather_cold));
                auxDesc = auxDesc + " - "+getResources().getString(R.string.title_recom_weather_cold) + "\n";
            }

            if(!check1 && !check2 && !check3){
                insertaTextoTiempo(" - "+getResources().getString(R.string.title_recom_weather_cold));
                auxDesc = auxDesc + " - "+getResources().getString(R.string.title_recom_weather_cold) + "\n";
            }

            auxDesc = auxDesc +"\n";
        }
        insertaLabelVacio();

        if(!guardado) {

            ConsultsDB consultsDB = new ConsultsDB(getContext());
            consultsDB.open();
            String t_min = tMin + " ºC";
            String t_max = tMax + " ºC";
            if(semana){
                consultsDB.updateConsulta(idConsulta,7,auxDesc,t_min,t_max);
            }else{
                consultsDB.updateConsulta(idConsulta,3,auxDesc,t_min,t_max);
            }
            consultsDB.close();
        }

        if(llueve){
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_8),8,false);
        }

        if(frio){
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_9),9,false);
        }

        if(calor){
            pintarRecomendacionesParaCriterio(getResources().getString(R.string.title_recom_10),10,false);
        }

        insertaLabelVacio();

        guardado = true;

        if(ra.consultaExistente){
            for(int i=0;i<recomsExists.size();i++){
                Long idRecom = recomsExists.get(i);
                if(!drawRecomsExists.contains(idRecom)){
                    RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
                    recomsForConsultDB.open();
                    recomsForConsultDB.deleteRecomendacionParaConsultaConIdRecomendacion(idRecom);
                    recomsForConsultDB.close();
                }
            }
        }

        return view;
    }

    private void pintarRecomendacionesParaCriterio(String titulo, int criterio, boolean pintarLineaSep){

        if(ra.consultaExistente){
            RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
            RecomsDB recomsDB = new RecomsDB(getContext());
            recomsForConsultDB.open();
            recomsDB.open();
            Cursor cursor = recomsForConsultDB.getRecomendacionesParaConsultaId(idConsulta);

            CriteryDB criteryDB = new CriteryDB(getContext());
            if((cursor != null) && (cursor.getCount() > 0)){
                int criterioRef = 99;
                criteryDB.open();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    final Long idRow = cursor.getLong(0);
                    Recom r = recomsDB.getRecomendacionForId(cursor.getLong(1));
                    int done = cursor.getInt(3);
                    if(!recomsExists.contains(r.getId())){
                        recomsExists.add(r.getId());
                    }
                    recomsRowsExists.put(r.getId(),idRow);
                    recomsCheckExists.put(r.getId(),done==1);
                }
                cursor.close();
                criteryDB.close();
            }
            recomsDB.close();
            recomsForConsultDB.close();
        }

        RecomsDB recomsDB = new RecomsDB(getContext());
        recomsDB.open();
        Cursor cursorRecomen = recomsDB.getRecomendacionForCriterio(criterio);

        if((cursorRecomen != null) && (cursorRecomen.getCount() > 0)){
            LinearLayout linearTitulo = new LinearLayout(getContext());
            linearTitulo.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams paramsLinearTitl= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsLinearTitl.setMargins(5,20,0,5);
            linearTitulo.setLayoutParams(paramsLinearTitl);
            linearTitulo.setGravity(Gravity.CENTER);

            ImageView imgTitle = new ImageView(getContext());
            RelativeLayout.LayoutParams paramsImg = new RelativeLayout.LayoutParams(100, 100);
            paramsImg.setMargins(0,0,0,0);
            imgTitle.setLayoutParams(paramsImg);

            int color = 0;
            switch (criterio){
                case 0:
                    imgTitle.setImageResource(R.drawable.cat_00);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_00);
                    break;
                case 1:
                    imgTitle.setImageResource(R.drawable.cat_01);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_01);
                    break;
                case 2:
                    imgTitle.setImageResource(R.drawable.cat_02);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_02);
                    break;
                case 3:
                    imgTitle.setImageResource(R.drawable.cat_03);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_03);
                    break;
                case 4:
                    imgTitle.setImageResource(R.drawable.cat_04);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_04);
                    break;
                case 5:
                    imgTitle.setImageResource(R.drawable.cat_05);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_05);
                    break;
                case 6:
                    imgTitle.setImageResource(R.drawable.cat_06);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_06);
                    break;
                case 7:
                    imgTitle.setImageResource(R.drawable.cat_07);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_07);
                    break;
                case 8:
                    imgTitle.setImageResource(R.drawable.cat_08);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_08);
                    break;
                case 9:
                    imgTitle.setImageResource(R.drawable.cat_09);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_09);
                    break;
                case 10:
                    imgTitle.setImageResource(R.drawable.cat_10);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_10);
                    break;
                case 11:
                    imgTitle.setImageResource(R.drawable.cat_11);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_11);
                    break;
                case 12:
                    imgTitle.setImageResource(R.drawable.cat_12);
                    color = ContextCompat.getColor(getContext(), R.color.colorCat_12);
                    break;
            }
            linearTitulo.addView(imgTitle);

            TextView tvTitle = new TextView(getContext());
            tvTitle.setText(titulo);
            TextViewCompat.setTextAppearance(tvTitle, R.style.Style_Recoms_Title);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,0,0,0);
            tvTitle.setLayoutParams(params);
            linearTitulo.addView(tvTitle);
            tvTitle.setTextColor(color);
            ll.addView(linearTitulo);

            RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
            recomsForConsultDB.open();

            for (cursorRecomen.moveToFirst(); !cursorRecomen.isAfterLast(); cursorRecomen.moveToNext()) {
                if(cursorRecomen.getInt(3)==0){
                    Long idRecom = cursorRecomen.getLong(0);

                    LinearLayout fila = new LinearLayout(getContext());
                    fila.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams paramsFila= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsFila.setMargins(10,0,0,5);
                    fila.setLayoutParams(paramsFila);
                    fila.setGravity(Gravity.CENTER);

                    //Guardar en consulta
                    if(!guardado){
                        Long idRow;
                        if(ra.consultaExistente && recomsExists.contains(idRecom)){
                            idRow = recomsRowsExists.get(idRecom);
                        }else{
                            idRow = recomsForConsultDB.createRecomendacionParaConsulta(idRecom,idConsulta,false);
                        }
                        indexRow.put(idRecom,idRow);
                    }

                    final CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setTag(idRecom);

                    if(ra.consultaExistente){
                        if(recomsExists.contains(idRecom)){
                            boolean checked = recomsCheckExists.get(idRecom);
                            checkBoxIndex.put(idRecom,checked);
                            checkBox.setChecked(checked);
                        }else{
                            checkBoxIndex.put(idRecom,false);
                            checkBox.setChecked(false);
                        }
                    }else{
                        if(!guardado){
                            checkBoxIndex.put(idRecom,checkBox.isChecked());
                        }else{
                            checkBox.setChecked(checkBoxIndex.get(idRecom));
                        }
                    }

                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
                            recomsForConsultDB.open();
                            Long idRecom = (Long) checkBox.getTag();
                            Long idRow = indexRow.get(idRecom);
                            if(idRecom!=null && idRow!=null){
                                boolean check = checkBox.isChecked();
                                checkBoxIndex.put(idRecom,check);
                                recomsForConsultDB.updateRecomendacionParaConsulta(idRow, idRecom,idConsulta,check);
                            }
                            recomsForConsultDB.close();

                        }
                    });
                    fila.addView(checkBox);

                    TextView tvRecomen = new TextView(getContext());
                    tvRecomen.setText(cursorRecomen.getString(1));
                    recomsDescriptions.add(cursorRecomen.getString(1));
                    TextViewCompat.setTextAppearance(tvRecomen, R.style.Style_Recoms);

                    LinearLayout.LayoutParams paramsAux = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsAux.setMargins(10,0,0,5);
                    tvRecomen.setLayoutParams(paramsAux);

                    fila.addView(tvRecomen);

                    if(ra.consultaExistente){
                        drawRecomsExists.add(idRecom);
                    }

                    ll.addView(fila);
                }
            }

            List<RecomsForConsult> recs = recomsForConsultDB.getRecomendacionParaConsulta();
            recomsForConsultDB.close();

            if(pintarLineaSep){
                LinearLayout llLinea = new LinearLayout(getContext());
                llLinea.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                LinearLayout.LayoutParams paramsLinea = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
                paramsLinea.setMargins(5,20,5,20);
                llLinea.setLayoutParams(paramsLinea);
                ll.addView(llLinea);
            }

        }
        cursorRecomen.close();
        recomsDB.close();
    }

    private void insertaTextoTiempo(String texto){
        TextView tvTiempo = new TextView(getContext());
        tvTiempo.setText(texto);
        TextViewCompat.setTextAppearance(tvTiempo, R.style.Style_Recoms);

        LinearLayout.LayoutParams paramsAux = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsAux.setMargins(10,0,0,5);
        tvTiempo.setLayoutParams(paramsAux);
        ll.addView(tvTiempo);
    }

    private int clasificaCodigoClima(String codigo){
        int result;
        switch (codigo){
            case "395": //395 Nieve moderada o fuerte en la zona con truenos
                result = 8;
                break;
            case "392": //392 Nieve ligera en zona con truenos
                result = 8;
                break;
            case "368": //368 Chubascos de nieve ligeros
                result = 8;
                break;
            case "338": //338 Nieve fuerte
                result = 8;
                break;
            case "332": //332 Nieve moderada
                result = 8;
                break;
            case "230": //230 Ventisca
                result = 8;
                break;
            case "227": //227 Nieve que sopla
                result = 8;
                break;
            case "179": //179 Nieve dispersa cerca
                result = 8;
                break;
            case "350": //350 granizo
                result = 8;
                break;
            case "320": //320 Aguanieve moderada o fuerte
                result = 8;
                break;
            case "317": //317 Aguanieve ligero
                result = 8;
                break;
            case "182": //182 Aguanieve ligera cerca
                result = 8;
                break;
            case "389": //389 Lluvia moderada o fuerte con truenos
                result = 8;
                break;
            case "386": //386 Lluvia ligera en zona con truenos
                result = 8;
                break;
            case "377": //377 Duchas moderadas o fuertes de pellets de hielo
                result = 8;
                break;
            case "374": //374 Duchas ligeras de pellets de hielo
                result = 8;
                break;
            case "371": //371 Probabilidad de lluvia
                result = 8;
                break;
            case "365": //365 Duchas de aguanieve moderada o fuerte
                result = 8;
                break;
            case "362": //362 Chubascos de nieve
                result = 8;
                break;
            case "359": //359 Tormentas de lluvia
                result = 8;
                break;
            case "353": //353 Lluvia ligera
                result = 8;
                break;
            case "329": //329 Probabilidad de lluvia
                result = 8;
                break;
            case "326": //326 Lluvia ligera
                result = 8;
                break;
            case "314": //314 Moderado o fuerte lluvia congelante
                result = 8;
                break;
            case "311": //311 Lluvia ligera
                result = 8;
                break;
            case "308": //308 Lluvia intensa
                result = 8;
                break;
            case "305": //305 Lluvia fuerte a veces
                result = 8;
                break;
            case "302": //302 Lluvia moderada
                result = 8;
                break;
            case "296": //296 Lluvia ligera
                result = 8;
                break;
            case "293": //293 Lluvia ligera
                result = 8;
                break;
            case "284": //284 Llovizna fuerte y llovizna
                result = 8;
                break;
            case "281": //281 Llovizna congelante
                result = 8;
                break;
            case "266": //266 Llovizna ligera
                result = 8;
                break;
            case "263": //263 Llovizna ligera
                result = 8;
                break;
            case "185": //185 Llovizna ligera cerca
                result = 8;
                break;
            case "356": //356 Parcialmente nublado
                result = 99;
                break;
            case "335": //335 Parcialmente nuboso
                result = 99;
                break;
            case "323": //323 Parcialmente nuboso
                result = 99;
                break;
            case "299": //299 Parcialmente nublado
                result = 99;
                break;
            case "260": //260 Niebla helada
                result = 99;
                break;
            case "248": //248 Niebla
                result = 99;
                break;
            case "143": //143 Niebla
                result = 99;
                break;
            case "200": //200 nubosidad dispersa en las inmediaciones
                result = 99;
                break;
            case "122": //122 Nublado
                result = 99;
                break;
            case "119": //119 Parcialmente nublado
                result = 99;
                break;
            case "116": //116 Parcialmente nublado
                result = 99;
                break;
            case "176": //176 Despejado
                result = 99;
                break;
            case "113": //113 Despejado
                result = 99;
                break;
            default:
                result = 99;
                break;
        }
        return  result;
    }

    private void insertaLabelVacio(){
        TextView tvkk = new TextView(getContext());
        tvkk.setText("");
        ll.addView(tvkk);
    }
}
