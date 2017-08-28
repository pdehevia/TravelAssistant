package proyect.travelassistant.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.MenuItem;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.fragments.FragmentHistoricalNoElems;
import proyect.travelassistant.fragments.FragmentHistoricalList;
import proyect.travelassistant.fragments.FragmentHistoricalResult;
import proyect.travelassistant.sqlite.Consult;
import proyect.travelassistant.sqlite.ConsultsDB;
import proyect.travelassistant.sqlite.RecomsForConsultDB;

public class HistoricalActivity extends BaseActivity {

    public List<Consult> consults;
    public int consultado;
    public boolean estoyEnListado = true;
    public boolean quieroBorrar = false;
    private Activity activity;
    int idItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_historical);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item3));

        Intent intent = getIntent();
        idItem = intent.getIntExtra("NotifID",-1);

        activity = this;

        ConsultsDB consultsDB = new ConsultsDB(getApplicationContext());
        consultsDB.open();
        consults = consultsDB.getConsultas();
        consultsDB.close();

        if(consults.size()>0){
            mostrarListadoFragment();
        }else{
            mostrarFragmentVacio();
        }
    }

    public void mostrarFragmentVacio(){
        FragmentHistoricalNoElems firstFragment = new FragmentHistoricalNoElems();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }

    public void mostrarResultadoFragment(){
        FragmentHistoricalResult newFragment = new FragmentHistoricalResult();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void mostrarListadoFragment(){
        FragmentHistoricalList newFragment = new FragmentHistoricalList();
        newFragment.selectItem = idItem;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void borrarConsultado(){
        ConsultsDB consultsDB = new ConsultsDB(getApplicationContext());
        consultsDB.open();
        consultsDB.deleteConsulta(consults.get(consultado).getId());
        RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(getApplicationContext());
        recomsForConsultDB.open();
        recomsForConsultDB.deleteRecomendacionParaConsultaConIdConsulta(consults.get(consultado).getId());
        recomsForConsultDB.close();
        consults = consultsDB.getConsultas();
        consultsDB.close();
    }

    @Override
    public void onBackPressed() {
        if(estoyEnListado){
            startActivity(new Intent(HistoricalActivity.this, IntroActivity.class));
            finish();
        }else{
            estoyEnListado = true;
            mostrarListadoFragment();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(estoyEnListado){
                    finish();
                }else{
                    estoyEnListado = true;
                    mostrarListadoFragment();
                }
                break;
            case R.id.action_del:
                new AlertDialog.Builder(activity)
                        .setTitle(getString(R.string.title_alert_del_consult))
                        .setMessage(getString(R.string.text_alert_del_consult))
                        .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                estoyEnListado = true;
                                quieroBorrar = true;
                                mostrarListadoFragment();
                            }
                        })
                        .setIcon(R.drawable.ic_warning)
                        .setNegativeButton(getString(R.string.cancel),null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_historical;
    }

}
