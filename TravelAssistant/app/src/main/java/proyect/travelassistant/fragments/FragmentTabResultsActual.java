package proyect.travelassistant.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.ResultActivity;
import proyect.travelassistant.adapters.RecyclerAdapterWeather;
import proyect.travelassistant.beans.worldweather.CurrentConditionBean;


public class FragmentTabResultsActual extends Fragment {

    private View view;
    private List<CurrentConditionBean> conditions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_results_actual, container, false);

        ResultActivity ra = (ResultActivity) getActivity();
        conditions = ra.response.getData().getCurrent_condition();

        //Campos tiempo de hoy
        ImageView img = (ImageView) view.findViewById(R.id.imageViewWeatherToday);
        Ion.with(img).error(R.drawable.pixel_vacio).load(conditions.get(0).getWeatherIconUrl().get(0).getValue());

        TextView tv_descripcion = (TextView) view.findViewById(R.id.textViewDescToday);
        tv_descripcion.setText(conditions.get(0).getLang_es().get(0).getValue());

        TextView tv_temperatura = (TextView) view.findViewById(R.id.textViewTempValor);
        tv_temperatura.setText(conditions.get(0).getTemp_C()+" ºC");

        TextView tv_sensacion = (TextView) view.findViewById(R.id.textViewSensationValue);
        tv_sensacion.setText(conditions.get(0).getFeelsLikeC()+" ºC");

        TextView tv_viento = (TextView) view.findViewById(R.id.textViewWindValue);
        tv_viento.setText(conditions.get(0).getWindspeedKmph()+" Km/h");

        TextView tv_humedad = (TextView) view.findViewById(R.id.textViewHumidityValue);
        tv_humedad.setText(conditions.get(0).getHumidity()+" %");

        TextView tv_horaAct = (TextView) view.findViewById(R.id.textViewUpdateValue);
        tv_horaAct.setText(conditions.get(0).getObservation_time());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerAdapterWeather adaptador = new RecyclerAdapterWeather(view.getContext(), ra.response.getData().getWeather());
        recyclerView.setAdapter(adaptador);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
