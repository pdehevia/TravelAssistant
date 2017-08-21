package proyect.travelassistant.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.adapters.RecyclerAdapterRecom;
import proyect.travelassistant.sqlite.Critery;
import proyect.travelassistant.sqlite.CriteryDB;
import proyect.travelassistant.sqlite.Recom;
import proyect.travelassistant.sqlite.RecomsDB;


public class RecomCustomActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private RecyclerAdapterRecom adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_recom_custom);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item4));

        RecomsDB recomsDB = new RecomsDB(this);
        recomsDB.open();
        List<Recom> recomendaciones = recomsDB.getRecomendaciones();
        recomsDB.close();

        CriteryDB criteryDB = new CriteryDB(this);
        criteryDB.open();
        List<Critery> criteries = criteryDB.getCriterios();
        criteryDB.close();

        if(recomendaciones.size()>0){
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_edit_recom);
            adaptador = new RecyclerAdapterRecom(this, recomendaciones, criteries,this);
            recyclerView.setAdapter(adaptador);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
        }

    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_edit_recommendations;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Recom item = adaptador.getItem(position);
        //TextView t = (TextView) view.findViewById(R.id.textoRecomendacion);
        //t.setText("CLICK");

        Intent intent = new Intent(this,EditRecomActivity.class);
        intent.putExtra("Item",item);
        this.startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Boolean edit=data.getBooleanExtra("Edit",false);
                if(edit){
                    RecomsDB recomsDB = new RecomsDB(this);
                    recomsDB.open();
                    List<Recom> recomendaciones = recomsDB.getRecomendaciones();
                    recomsDB.close();
                    if(recomendaciones.size()>0){
                        adaptador.setListaRecomendaciones(recomendaciones);
                        adaptador.notifyDataSetChanged();
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_add:
                Intent intent = new Intent(this,AddRecomActivity.class);
                this.startActivityForResult(intent,1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RecomCustomActivity.this, IntroActivity.class));
        finish();
    }
}
