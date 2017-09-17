package proyect.travelassistant.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.sqlite.CustomRecomsForConsult;
import proyect.travelassistant.sqlite.CustomRecomsForConsultDB;

/**
 * Created by pgarcia on 14/9/17.
 */

public class FragmentCustomRecomCreate extends DialogFragment {
    private View mView;
    private EditText customRecomEt;
    private Button buttonCancelCustomRecomCreate;
    private Button buttonSaveCustomRecomCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_custom_recom_create, container, false);
        customRecomEt = (EditText) mView.findViewById(R.id.customRecomEt);
        buttonCancelCustomRecomCreate = (Button) mView.findViewById(R.id.buttonCancelCustomRecomCreate);
        buttonSaveCustomRecomCreate = (Button) mView.findViewById(R.id.buttonSaveCustomRecomCreate);

        //Click Cancel
        buttonCancelCustomRecomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //Click Save
        buttonSaveCustomRecomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = customRecomEt.getText().toString();
                if(description.length()>0){
                    Long idConsult = AuxiliarData.getSingletonInstance().getConsultId();
                    CustomRecomsForConsultDB customRecomsForConsultDB = new CustomRecomsForConsultDB(getContext());
                    customRecomsForConsultDB.open();
                    Long id = customRecomsForConsultDB.createCustomRecomForConsult(idConsult,description,false);
                    customRecomsForConsultDB.close();

                    AuxiliarData.getSingletonInstance().setUpdateCustomRecoms(true);
                    AuxiliarData.getSingletonInstance().setCustomRecom(new CustomRecomsForConsult(id,idConsult, description,false));
                }else{
                    new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.title_error_custom_recom))
                            .setMessage(getString(R.string.text_error_custom_recom))
                            .setPositiveButton(getString(R.string.accept), null)
                            .setIcon(R.drawable.ic_warning)
                            .show();
                }

                dismiss();
            }
        });
        return mView;
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

}
