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
        String tit = intent.getStringExtra("TIT_NOTIF");
        String txt = intent.getStringExtra("TXT_NOTIF");


        NotificationScheduler.desactiveNotifState(context,id);
        NotificationScheduler.showNotification(context,IntroActivity.class,id, tit, txt);
    }
}
