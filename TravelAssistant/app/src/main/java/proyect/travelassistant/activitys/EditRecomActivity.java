package proyect.travelassistant.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.Critery;
import proyect.travelassistant.sqlite.CriteryDB;
import proyect.travelassistant.sqlite.Recom;
import proyect.travelassistant.sqlite.RecomsForConsultDB;
import proyect.travelassistant.sqlite.RecomsDB;


public class EditRecomActivity extends BaseActivity {

    private Recom item;
    private Activity activity;
    private EditText editText;
    private Spinner spinner;
    private Switch switchVisible;
    private Button btnCancelar;
    private Button btnGuardar;
    private ImageView imgBorrar;

    private List<String> criteriosDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_edit_recom);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item4));
        setSecondLevelToolbar(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        activity = this;

        Intent intent = getIntent();
        item = (Recom) intent.getSerializableExtra("Item");

        editText = (EditText) findViewById(R.id.editTextEditRecomTxt) ;
        spinner = (Spinner) findViewById(R.id.spinner_edit_rec);
        switchVisible = (Switch) findViewById(R.id.switchEditRecom);
        imgBorrar = (ImageView) findViewById(R.id.imageDelete);

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

        if(item!=null){
            editText.setText(item.getDescripcion());
            spinner.setSelection((int) item.getCritero());
            switchVisible.setChecked(item.isVisible());
        }

        btnCancelar = (Button) findViewById(R.id.buttonCancelEditRecom);
        btnGuardar = (Button) findViewById(R.id.buttonSaveEditRecom);

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
                    if(switchVisible.isChecked()){
                        item.setVisible(true);
                    }else{
                        item.setVisible(false);
                    }
                    item.setDescripcion(editText.getText().toString());
                    item.setCritero(spinner.getSelectedItemId());

                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_alert_save))
                            .setMessage(getString(R.string.text_alert_save))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    RecomsDB recomsDB = new RecomsDB(activity);
                                    recomsDB.open();
                                    recomsDB.updateRecomendacion(item.getId(),item.getDescripcion(),item.getCritero(),item.isVisible());
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
                            .setIcon(R.drawable.ic_warning)
                            .show();
                }

            }
        });

        imgBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity)
                        .setTitle(getString(R.string.title_alert_delete))
                        .setMessage(getString(R.string.text_alert_delete))
                        .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                RecomsDB recomsDB = new RecomsDB(activity);
                                recomsDB.open();
                                RecomsForConsultDB recomsForConsultDB = new RecomsForConsultDB(activity);
                                recomsForConsultDB.open();

                                recomsForConsultDB.deleteRecomendacionParaConsultaConIdRecomendacion(item.getId());
                                recomsDB.deleteRecomendacion(item.getId());

                                recomsForConsultDB.close();
                                recomsDB.close();

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("Edit",true);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }
                        })
                        .setIcon(R.drawable.ic_warning)
                        .setNegativeButton(getString(R.string.cancel),null)
                        .show();
            }
        });
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_edit_recommendations;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
