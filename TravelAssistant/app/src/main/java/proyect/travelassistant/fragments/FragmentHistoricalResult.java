package proyect.travelassistant.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.HistoricalActivity;
import proyect.travelassistant.activitys.ResultActivity;
import proyect.travelassistant.beans.HistoricalFileBean;
import proyect.travelassistant.beans.worldweather.Response;
import proyect.travelassistant.sqlite.Consult;
import proyect.travelassistant.sqlite.CriteryDB;
import proyect.travelassistant.sqlite.RecomsForConsultDB;
import proyect.travelassistant.sqlite.RecomsDB;
import proyect.travelassistant.utils.RestClient;


public class FragmentHistoricalResult extends Fragment {

    private View view;
    private LinearLayout ll;
    private Response response;
    private Activity activity;

    private Consult consult;
    private String critery1;
    private String critery2;
    private String critery3;
    private String critery4;

    private final int NUM_CRITEROS = 12;

    private List<HistoricalFileBean>files = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_historical_result, container, false);
        ll = (LinearLayout) view.findViewById(R.id.llayout_historical_result);

        HistoricalActivity ha = (HistoricalActivity) getActivity();
        consult = ha.consults.get(ha.consultado);

        TextView tvTitle = new TextView(getContext());
        tvTitle.setText(consult.getDestino() + " ("+ consult.getFecha()+")");
        TextViewCompat.setTextAppearance(tvTitle, R.style.Style_Title_Historic);
        LinearLayout.LayoutParams par1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par1.setMargins(10,10,10,10);
        tvTitle.setLayoutParams(par1);
        ll.addView(tvTitle);

        Button btnUpdate = new Button(getContext());
        btnUpdate.setText(getString(R.string.update_button));
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(10,10,10,10);
        btnUpdate.setLayoutParams(btnParams);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarRed()){
                    new updateQueryTask().execute();
                }
            }
        });
        ll.addView(btnUpdate);

        TextView tvTitleRec = new TextView(getContext());
        tvTitleRec.setText(getResources().getString(R.string.title_recom_hist));
        TextViewCompat.setTextAppearance(tvTitleRec, R.style.Style_Recoms_Edit);
        LinearLayout.LayoutParams par3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par3.setMargins(10,10,10,2);
        tvTitleRec.setLayoutParams(par3);
        ll.addView(tvTitleRec);

        TextView tvsubTitleRec = new TextView(getContext());
        tvsubTitleRec.setText(getResources().getString(R.string.subtitle_recom_hist)+"\n");
        TextViewCompat.setTextAppearance(tvsubTitleRec, R.style.Style_Days_Historic);
        LinearLayout.LayoutParams par4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par4.setMargins(15,0,0,10);
        tvsubTitleRec.setLayoutParams(par4);
        ll.addView(tvsubTitleRec);

        TextView tvDias = new TextView(getContext());
        tvDias.setText(getResources().getString(R.string.duration_travel_hist)+" "+ consult.getDias()+" "+getResources().getString(R.string.days)+".");
        TextViewCompat.setTextAppearance(tvDias, R.style.Style_Days_Historic);
        LinearLayout.LayoutParams par2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par2.setMargins(10,0,0,10);
        tvDias.setLayoutParams(par2);
        ll.addView(tvDias);

        TextView tvTminTit = new TextView(getContext());
        tvTminTit.setText(getResources().getString(R.string.temperature_min_days));
        TextViewCompat.setTextAppearance(tvTminTit, R.style.Style_DaysRec_Historic);
        LinearLayout.LayoutParams parTMinTit = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parTMinTit.setMargins(10,0,0,10);
        tvTminTit.setLayoutParams(parTMinTit);
        ll.addView(tvTminTit);

        TextView tvTmin = new TextView(getContext());
        tvTmin.setText(consult.getTemMin());
        TextViewCompat.setTextAppearance(tvTmin, R.style.Style_Min_Temp);
        LinearLayout.LayoutParams parTMin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parTMin.setMargins(10,0,0,10);
        tvTmin.setLayoutParams(parTMin);
        ll.addView(tvTmin);

        TextView tvTmaxTit = new TextView(getContext());
        tvTmaxTit.setText(getResources().getString(R.string.temperature_max_days));
        TextViewCompat.setTextAppearance(tvTmaxTit, R.style.Style_DaysRec_Historic);
        LinearLayout.LayoutParams parTMaxTit = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parTMaxTit.setMargins(10,0,0,10);
        tvTmaxTit.setLayoutParams(parTMaxTit);
        ll.addView(tvTmaxTit);

        TextView tvTmax = new TextView(getContext());
        tvTmax.setText(consult.getTemMax());
        TextViewCompat.setTextAppearance(tvTmax, R.style.Style_Max_Temp);
        LinearLayout.LayoutParams parTMax = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parTMax.setMargins(10,0,0,30);
        tvTmax.setLayoutParams(parTMax);
        ll.addView(tvTmax);

        TextView tvTiempo = new TextView(getContext());
        tvTiempo.setText(consult.getDescDias());
        TextViewCompat.setTextAppearance(tvTiempo, R.style.Style_Days_Historic);
        LinearLayout.LayoutParams par5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par5.setMargins(10,0,0,10);
        tvTiempo.setLayoutParams(par5);
        ll.addView(tvTiempo);

        RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
        RecomsDB recomsDB = new RecomsDB(getContext());
        recomsForConsultDB.open();
        recomsDB.open();
        final Long idConsulta = consult.getId();
        Cursor cursor = recomsForConsultDB.getRecomendacionesParaConsultaId(idConsulta);


        if((cursor != null) && (cursor.getCount() > 0)){
            int criterioRef = 99;
            files.clear();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                HistoricalFileBean hfb = new HistoricalFileBean();
                hfb.setIdRow(cursor.getLong(0));
                hfb.setRecom(recomsDB.getRecomendacionForId(cursor.getLong(1)));
                int done = cursor.getInt(3);
                hfb.setDone(done==1);
                files.add(hfb);
            }
            cursor.close();

            orderFilesByCritery();

            CriteryDB criteryDB = new CriteryDB(getContext());
            criteryDB.open();
            for (int i=0;i<files.size();i++) {
                final HistoricalFileBean hfb = files.get(i);

                int nuevoCrit = (int) hfb.getRecom().getCritero();
                if(nuevoCrit!=criterioRef){
                    criterioRef = nuevoCrit;

                    pintarLinea();

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
                    switch ((int) hfb.getRecom().getCritero()){
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
                            critery2 = getString(R.string.critery_3);
                            break;
                        case 4:
                            imgTitle.setImageResource(R.drawable.cat_04);
                            color = ContextCompat.getColor(getContext(), R.color.colorCat_04);
                            critery2 = getString(R.string.critery_4);
                            break;
                        case 5:
                            imgTitle.setImageResource(R.drawable.cat_05);
                            color = ContextCompat.getColor(getContext(), R.color.colorCat_05);
                            critery3 = getString(R.string.critery_5);
                            break;
                        case 6:
                            imgTitle.setImageResource(R.drawable.cat_06);
                            color = ContextCompat.getColor(getContext(), R.color.colorCat_06);
                            critery3 = getString(R.string.critery_6);
                            break;
                        case 7:
                            imgTitle.setImageResource(R.drawable.cat_07);
                            color = ContextCompat.getColor(getContext(), R.color.colorCat_07);
                            critery3 = getString(R.string.critery_7);
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
                            critery4 = getString(R.string.critery_11);
                            break;
                        case 12:
                            imgTitle.setImageResource(R.drawable.cat_12);
                            color = ContextCompat.getColor(getContext(), R.color.colorCat_12);
                            critery4 = getString(R.string.critery_12);
                            break;
                    }
                    linearTitulo.addView(imgTitle);

                    TextView tvTitleCrit = new TextView(getContext());
                    tvTitleCrit.setText(criteryDB.getCriterioDescripcionForId(hfb.getRecom().getCritero()));
                    TextViewCompat.setTextAppearance(tvTitleCrit, R.style.Style_Recoms_Title);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10,0,0,0);
                    tvTitleCrit.setLayoutParams(params);
                    linearTitulo.addView(tvTitleCrit);
                    tvTitleCrit.setTextColor(color);
                    ll.addView(linearTitulo);
                }

                LinearLayout fila = new LinearLayout(getContext());
                fila.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams paramsFila= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsFila.setMargins(10,0,0,5);
                fila.setLayoutParams(paramsFila);

                final CheckBox checkBox = new CheckBox(getContext());
                checkBox.setTag(hfb.getRecom().getId());
                checkBox.setChecked(hfb.isDone());
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getContext());
                        recomsForConsultDB.open();
                        Long idRecom = (Long) checkBox.getTag();
                        if(idRecom!=null && hfb.getIdRow()!=null){
                            boolean check = checkBox.isChecked();
                            recomsForConsultDB.updateRecomendacionParaConsulta(hfb.getIdRow(), idRecom,idConsulta,check);
                        }
                        recomsForConsultDB.close();
                    }
                });

                fila.addView(checkBox);

                TextView tvRecomen = new TextView(getContext());
                tvRecomen.setText(hfb.getRecom().getDescripcion());
                TextViewCompat.setTextAppearance(tvRecomen, R.style.Style_Recoms_Edit);
                LinearLayout.LayoutParams paramsAux = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsAux.setMargins(10,0,0,5);
                tvRecomen.setLayoutParams(paramsAux);
                fila.addView(tvRecomen);

                ll.addView(fila);
            }
            criteryDB.close();
        }
        recomsDB.close();
        recomsForConsultDB.close();

        return view;
    }

    private void pintarLinea(){
        LinearLayout llLinea = new LinearLayout(getContext());
        llLinea.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        LinearLayout.LayoutParams paramsLinea = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        paramsLinea.setMargins(5,20,5,20);
        llLinea.setLayoutParams(paramsLinea);
        ll.addView(llLinea);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_del, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static ProgressDialog getCustomProgressDialog(Context mContext, String texto) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(texto);
        return progressDialog;
    }

    private class updateQueryTask extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = getCustomProgressDialog(activity, getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key="+getString(R.string.key)+"&q="+consult.getLat()+","+consult.getLon()+"&format=json&num_of_days="+consult.getDias()+"&lang=es";
            try {
                String responseString =  RestClient.getJsonResponse(url);
                int i=0;
                while(i<3){
                    i++;
                    if(responseString.equals("")){
                        responseString =  RestClient.getJsonResponse(url);
                    }else{
                        i=3;
                    }
                }
                return responseString;

            } catch (Exception e) {
                Log.e("SERVICE ERROR",""+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                if(result!=null && result!=" " && result!=""){
                    response = new Gson().fromJson(result, Response.class);
                    if(response!=null){

                        if(consult.getDias()==3){
                            critery1= getString(R.string.critery_1);
                        }else{
                            critery1= getString(R.string.critery_2);
                        }
                        Intent i = new Intent(activity, ResultActivity.class);
                        i.putExtra("Response",response);
                        i.putExtra("Duracion", critery1);
                        i.putExtra("Motivo",critery2);
                        i.putExtra("Alojamiento",critery3);
                        i.putExtra("Transporte",critery4);
                        i.putExtra("Destino",consult.getDestino());
                        i.putExtra("ConsultaExiste",true);
                        i.putExtra("ConsultaID",consult.getId());
                        activity.startActivity(i);
                        activity.finish();
                    }else{
                        //ERROR
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_service))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                    }
                }else{
                    //ERROR
                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_error_service))
                            .setMessage(getString(R.string.text_error_service))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                }
            } catch (Exception e) {
                Log.e("onPostExecute ERROR",""+e);
            }
        }
    }

    private boolean comprobarRed(){
        ConnectivityManager cm = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = false;
        if(activeNetwork!=null){
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        if(!isConnected){
            new AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.title_error_red))
                    .setMessage(getString(R.string.text_error_red))
                    .setPositiveButton(getString(R.string.reset), new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            if(comprobarRed()){
                                new updateQueryTask().execute();
                            }
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
        return isConnected;
    }

    private void orderFilesByCritery(){
        List<HistoricalFileBean> orderFiles = new ArrayList<>();
        for(long i=0;i<=NUM_CRITEROS;i++){
            for(int j=0;j<files.size();j++){
                if(files.get(j).getRecom().getCritero()==i){
                    orderFiles.add(files.get(j));
                }
            }
        }
        if(orderFiles.size()>0){
            files = orderFiles;
        }
    }

}

