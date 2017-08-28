package proyect.travelassistant.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import proyect.travelassistant.activitys.IntroActivity;

/**
 * Created by Pablo on 28/08/2017.
 */

public class AlarmReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("ID_NOTIF",-1);
        int hour = intent.getIntExtra("HH_NOTIF",-1);
        int min = intent.getIntExtra("MM_NOTIF",-1);
        String tit = intent.getStringExtra("TIT_NOTIF");
        String txt = intent.getStringExtra("TXT_NOTIF");

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.

                if(id>0 && hour>0 && min>0){
                   // NotificationScheduler.setReminder(context, AlarmReceiver.class,id, hour, min, tit, txt);
                }

                return;
            }
        }

        //Trigger the notification
        NotificationScheduler.showNotification(context,IntroActivity.class,id, tit, txt);
    }
}
