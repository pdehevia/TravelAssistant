package proyect.travelassistant.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.ResultActivity;
import proyect.travelassistant.adapters.GridAdapter;
import proyect.travelassistant.beans.worldweather.MonthBean;


public class FragmentTabResultsHistorical extends Fragment {

    private View view;
    private List<MonthBean> months;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ResultActivity ra = (ResultActivity) getActivity();
        months = ra.response.getData().getClimateAverages().get(0).getMonth();

        view = inflater.inflate(R.layout.fragment_tab_resultados_historico, container, false);

        GridView grid=(GridView) view.findViewById(R.id.grid_tab_1);
        grid.setAdapter(new GridAdapter(this.getContext(),months));

        return view;
    }
}