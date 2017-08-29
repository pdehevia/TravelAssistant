package proyect.travelassistant.fragments;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.beans.ScheduledInfoBean;
import proyect.travelassistant.broadcastreceiver.AlarmReceiver;
import proyect.travelassistant.broadcastreceiver.NotificationScheduler;
import proyect.travelassistant.sqlite.NotifForConsult;
import proyect.travelassistant.sqlite.NotifForConsultDB;

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
    private boolean existNotifOnCharge;
    private boolean checkedOld;
    private int indexRecom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule_notification, container, false);
        existNotifOnCharge = false;
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
            existNotifOnCharge = true;
            checkedOld =scheduledInfo.getNfc().isActiva();
            scheduleSwitch.setChecked(scheduledInfo.getNfc().isActiva());

            String horaMin = scheduledInfo.getNfc().getHora();
            scheduleTvHour.setText(horaMin);
            if(horaMin.length()==4){
                hourSchedule = Integer.parseInt(horaMin.substring(0,1));
                minSchedule = Integer.parseInt(horaMin.substring(2,4));
            }else{
                hourSchedule = Integer.parseInt(horaMin.substring(0,2));
                minSchedule = Integer.parseInt(horaMin.substring(3,5));
            }


            schedule_spinner_type.setSelection(scheduledInfo.getNfc().getTipo());
            String recom = scheduledInfo.getNfc().getTexto().replace(getString(R.string.scheduled_type_recom_desc_1),"");
            recom = recom.replace(getString(R.string.scheduled_type_recom_desc_2),"");
            recom = recom.replace(scheduledInfo.getNameCity(),"").trim();

            indexRecom =0;
            for(int i=0; i<scheduledInfo.getRecoms().size();i++){
                if(recom.equals(scheduledInfo.getRecoms().get(i))){
                    indexRecom = i;
                    break;
                }
            }
            schedule_spinner_recom.setSelection(indexRecom);
        }else{
            //Default config
            scheduleSwitch.setChecked(false);
            scheduleTvHour.setText(getString(R.string.scheduled_hour_default));
            hourSchedule =12;
            minSchedule =00;
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

                //Obtain ACTIVE
                final boolean active = scheduleSwitch.isChecked();

                if(!checkedOld && !active){
                    new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.scheduled_title_save))
                            .setMessage(getString(R.string.scheduled_text_save))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    updateAlarmState(active);
                                    dismiss();
                                    Toast.makeText(getActivity(), getString(R.string.toast_text), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setIcon(R.drawable.ic_warning)
                            .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .setCancelable(false)
                            .show();
                }else{
                    updateAlarmState(active);
                    dismiss();
                    Toast.makeText(getActivity(), getString(R.string.toast_text), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mView;
    }

    private void updateAlarmState(boolean active) {
        //Obtain DATE
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        //Obtain HOUR
        String hour = scheduleTvHour.getText().toString();
        //Obtain TEXT
        String text = scheduleEt.getText().toString();
        //Obtain TYPE
        int type = schedule_spinner_type.getSelectedItemPosition();

        //Update bean
        scheduledInfo.getNfc().setHora(hour);
        scheduledInfo.getNfc().setTexto(text);
        scheduledInfo.getNfc().setTipo(type);

        //update BBDD
        NotifForConsultDB nfcDB = new NotifForConsultDB(getContext());
        nfcDB.open();
        nfcDB.updateNotificacionParaConsulta(scheduledInfo.getNfc().getId(),scheduledInfo.getNfc().getConsulta(),
                scheduledInfo.getNfc().getNotificacion(),today,hour,text,active,type);
        nfcDB.close();

        if(scheduleSwitch.isChecked()){
            NotificationScheduler.setReminder(getContext(), AlarmReceiver.class,(int)scheduledInfo.getNfc().getNotificacion(), hourSchedule, minSchedule,getString(R.string.notification_title),scheduleEt.getText().toString());
        }else{
            NotificationScheduler.cancelReminder(getContext(),AlarmReceiver.class,(int)scheduledInfo.getNfc().getNotificacion());
        }
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

            int index = 0;
            if(existNotifOnCharge){
                index = indexRecom;
            }

            schedule_spinner_recom.setSelection(index);

            scheduleEt.setText(getString(R.string.scheduled_type_recom_desc_1) + " " + scheduledInfo.getRecoms().get(index)
                    + " " + getString(R.string.scheduled_type_recom_desc_2)+ " " + scheduledInfo.getNameCity());
        }
        else  if(position == NotifForConsult.CUSTOM_TYPE){
            schedule_ll_select_recom.setVisibility(View.INVISIBLE);
            scheduleEt.setEnabled(true);

            String text = "";
            if(existNotifOnCharge){
                text = scheduledInfo.getNfc().getTexto();
            }
            scheduleEt.setText(text);
        }
        existNotifOnCharge = false;
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
                        String h = ""+hour;
                        String m = ""+min;
                        if(h.length()==1){
                            h="0"+h;
                        }
                        if(m.length()==1){
                            m="0"+m;
                        }
                        scheduleTvHour.setText(h+":"+m);
                    }
                }, h, m, true);

        builder.setCustomTitle(view);
        builder.show();

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
