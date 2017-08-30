package proyect.travelassistant.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.HistoricalActivity;
import proyect.travelassistant.adapters.RecyclerAdapterHistorial;
import proyect.travelassistant.beans.AuxiliarData;

public class FragmentHistoricalList extends Fragment implements AdapterView.OnItemClickListener {

    private View view;
    private HistoricalActivity ha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_historical_list, container, false);

        ha = (HistoricalActivity) getActivity();
        ha.setFirstLevelToolbar(getResources().getString(R.string.menu_item3));

        if(ha.quieroBorrar){
            ha.quieroBorrar = false;
            ha.borrarConsultado();
        }

        if(ha.consults.size()>0){
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_historical);
            RecyclerAdapterHistorial adaptador = new RecyclerAdapterHistorial(view.getContext(), ha.consults,this);
            recyclerView.setAdapter(adaptador);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);

        }else{
            ha.mostrarFragmentVacio();
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(AuxiliarData.getSingletonInstance().getItemId()!=-1){
            int selectItem = AuxiliarData.getSingletonInstance().getItemId();
            AuxiliarData.getSingletonInstance().setItemId(-1);
            for(int i=0;i<ha.consults.size();i++){
                if(ha.consults.get(i).getId()==(selectItem-100)){
                    goToSelect(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goToSelect(position);
    }

    private void goToSelect(int position){
        HistoricalActivity ha = (HistoricalActivity) getActivity();
        ha.consultado = position;
        ha.estoyEnListado = false;
        ha.mostrarResultadoFragment();
        ha.setSecondLevelToolbar(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
}
