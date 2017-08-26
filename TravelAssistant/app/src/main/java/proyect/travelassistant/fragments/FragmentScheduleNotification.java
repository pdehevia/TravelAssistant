package proyect.travelassistant.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.beans.ScheduledInfoBean;

public class FragmentScheduleNotification extends DialogFragment {
    private View mView;
    private Switch scheduleSwitch;
    private LinearLayout schedule_ll_select_hour;
    private TextView scheduleTvHour;
    private Spinner schedule_spinner_type;
    private LinearLayout schedule_ll_select_recom;
    private Spinner schedule_spinner_recom;
    private EditText scheduleEt;
    private ScheduledInfoBean scheduledInfo;
    private Button buttonCancelEditSchedule;
    private Button buttonSaveEditSchedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule_notification, container, false);

        scheduleSwitch = (Switch) mView.findViewById(R.id.scheduleSwitch);
        schedule_ll_select_hour = (LinearLayout) mView.findViewById(R.id.schedule_ll_select_hour);
        scheduleTvHour = (TextView) mView.findViewById(R.id.scheduleTvHour);
        schedule_spinner_type = (Spinner) mView.findViewById(R.id.schedule_spinner_type);
        schedule_ll_select_recom = (LinearLayout) mView.findViewById(R.id.schedule_ll_select_recom);
        schedule_spinner_recom = (Spinner) mView.findViewById(R.id.schedule_spinner_recom);
        scheduleEt = (EditText) mView.findViewById(R.id.scheduleEt);
        buttonCancelEditSchedule = (Button) mView.findViewById(R.id.buttonCancelEditSchedule);
        buttonSaveEditSchedule = (Button) mView.findViewById(R.id.buttonSaveEditSchedule);

        scheduledInfo = AuxiliarData.getSingletonInstance().getScheduledInfo();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.notification_options_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schedule_spinner_type.setAdapter(adapter1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_category_item, scheduledInfo.getRecoms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schedule_spinner_recom.setAdapter(adapter);

        //Default config
        scheduleSwitch.setChecked(false);
        scheduleTvHour.setText(getString(R.string.scheduled_hour_default));
        schedule_ll_select_recom.setVisibility(View.INVISIBLE);
        scheduleEt.setEnabled(false);
        scheduleEt.setText(getString(R.string.scheduled_type_def_desc) +" "+ scheduledInfo.getNameCity());

        //Click change hour
        schedule_ll_select_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Click spinner type
        schedule_spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0: //Por defecto
                        schedule_ll_select_recom.setVisibility(View.INVISIBLE);
                        scheduleEt.setEnabled(false);
                        scheduleEt.setText(getString(R.string.scheduled_type_def_desc) + " " +scheduledInfo.getNameCity());
                        break;
                    case 1: //Recomendación concreta
                        schedule_ll_select_recom.setVisibility(View.VISIBLE);
                        scheduleEt.setEnabled(false);
                        schedule_spinner_recom.setSelection(0);
                        scheduleEt.setText(scheduledInfo.getRecoms().get(0));
                        break;
                    case 2: //Personalizada
                        schedule_ll_select_recom.setVisibility(View.INVISIBLE);
                        scheduleEt.setEnabled(true);
                        scheduleEt.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        //Click spinner recom
        schedule_spinner_recom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(schedule_spinner_type.getSelectedItemPosition()==1){
                    scheduleEt.setText(scheduledInfo.getRecoms().get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        //Click Cancel
        buttonCancelEditSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //Click Save
        buttonSaveEditSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return mView;
    }

}
