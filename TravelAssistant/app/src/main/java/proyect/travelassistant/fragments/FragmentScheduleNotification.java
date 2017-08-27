package proyect.travelassistant.fragments;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.os.Build;
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
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.beans.ScheduledInfoBean;
import proyect.travelassistant.sqlite.NotifForConsult;

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

    private int hourSchedule;
    private int minSchedule;

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

        if(scheduledInfo.getNfc()!=null && scheduledInfo.getNfc().getTipo()!= NotifForConsult.NO_ACTIVE_TYPE){
            //Existe notificacion
            scheduleSwitch.setChecked(scheduledInfo.getNfc().isActiva());

            String horaMin = scheduledInfo.getNfc().getHora();
            scheduleTvHour.setText(horaMin);
            hourSchedule = Integer.parseInt(horaMin.substring(0,1));
            minSchedule = Integer.parseInt(horaMin.substring(3,4));
            drawComponentsByType(scheduledInfo.getNfc().getTipo());
        }else{
            //Default config
            scheduleSwitch.setChecked(false);
            scheduleTvHour.setText(getString(R.string.scheduled_hour_default));
            hourSchedule =12;
            minSchedule =12;
            schedule_ll_select_recom.setVisibility(View.INVISIBLE);
            scheduleEt.setEnabled(false);
            scheduleEt.setText(getString(R.string.scheduled_type_def_desc) +" "+ scheduledInfo.getNameCity());
        }

        //Click change hourSchedule
        schedule_ll_select_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Click spinner type
        schedule_spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                drawComponentsByType(position);
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
                    scheduleEt.setText(getString(R.string.scheduled_type_recom_desc_1) + " " + scheduledInfo.getRecoms().get(position)
                            + " " + getString(R.string.scheduled_type_recom_desc_2)+ " " + scheduledInfo.getNameCity());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        //Click hourSchedule

        schedule_ll_select_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(hourSchedule, minSchedule);
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
                //TODO: CREAR NOTIFICACION
            }
        });
        return mView;
    }

    private void drawComponentsByType(int position) {
        if(position == NotifForConsult.DEFAULT_TYPE){
            schedule_ll_select_recom.setVisibility(View.INVISIBLE);
            scheduleEt.setEnabled(false);
            scheduleEt.setText(getString(R.string.scheduled_type_def_desc) + " " +scheduledInfo.getNameCity());
        }
        else  if(position == NotifForConsult.RECOM_TYPE){
            schedule_ll_select_recom.setVisibility(View.VISIBLE);
            scheduleEt.setEnabled(false);
            schedule_spinner_recom.setSelection(0);

            scheduleEt.setText(getString(R.string.scheduled_type_recom_desc_1) + " " + scheduledInfo.getRecoms().get(0)
                    + " " + getString(R.string.scheduled_type_recom_desc_2)+ " " + scheduledInfo.getNameCity());
        }
        else  if(position == NotifForConsult.CUSTOM_TYPE){
            schedule_ll_select_recom.setVisibility(View.INVISIBLE);
            scheduleEt.setEnabled(true);
            scheduleEt.setText("");
        }
    }

    private void showTimePickerDialog(int h, int m) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker, null);

        TimePickerDialog builder = new TimePickerDialog(getContext(), R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        hourSchedule = hour;
                        minSchedule = min;
                        scheduleTvHour.setText(getFormatedTime(hour, min));
                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }
    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
}
