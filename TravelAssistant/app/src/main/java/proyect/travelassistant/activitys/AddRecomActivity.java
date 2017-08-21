package proyect.travelassistant.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.Critery;
import proyect.travelassistant.sqlite.CriteryDB;
import proyect.travelassistant.sqlite.Recom;
import proyect.travelassistant.sqlite.RecomsDB;

public class AddRecomActivity extends BaseActivity {

    private Activity activity;
    private EditText editText;
    private Spinner spinner;
    private Switch switchVisible;
    private Button btnCancelar;
    private Button btnGuardar;

    private List<String> criteriosDescripcion;
    private Recom item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_add_recom);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item4));
        activity = this;

        editText = (EditText) findViewById(R.id.editTextAddRecomTxt) ;
        spinner = (Spinner) findViewById(R.id.spinner_add_rec);
        switchVisible = (Switch) findViewById(R.id.switchAddRecom);

        CriteryDB criteryDB = new CriteryDB(this);
        criteryDB.open();
        List<Critery> criteries = criteryDB.getCriterios();
        criteryDB.close();

        criteriosDescripcion = new ArrayList<>();
        int i =0;
        for(Critery crit: criteries){
            criteriosDescripcion.add(i,crit.getDescripcion());
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_category_item, criteriosDescripcion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnCancelar = (Button) findViewById(R.id.buttonCancelAddRecom);
        btnGuardar = (Button) findViewById(R.id.buttonSaveAddRecom);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length()>0){
                    item = new Recom();

                    if(switchVisible.isChecked()){
                        item.setVisible(true);
                    }else{
                        item.setVisible(false);
                    }
                    item.setDescripcion(editText.getText().toString());
                    item.setCritero(spinner.getSelectedItemId());

                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_alert_create))
                            .setMessage(getString(R.string.text_alert_create))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    RecomsDB recomsDB = new RecomsDB(activity);
                                    recomsDB.open();
                                    recomsDB.createRecomendacion(item.getDescripcion(),item.getCritero(),item.isVisible());
                                    recomsDB.close();

                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("Edit",true);
                                    setResult(Activity.RESULT_OK,returnIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(getString(R.string.cancel),null)
                            .show();
                }else{
                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_alert_desc))
                            .setMessage(getString(R.string.text_alert_desc))
                            .setPositiveButton(getString(R.string.accept), null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_edit_recommendations;
    }
}
