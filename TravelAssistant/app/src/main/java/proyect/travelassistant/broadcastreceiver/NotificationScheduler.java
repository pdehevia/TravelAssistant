package proyect.travelassistant.broadcastreceiver;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.NotifForConsult;
import proyect.travelassistant.sqlite.NotifForConsultDB;

import static android.content.Context.ALARM_SERVICE;


/**
 * Created by Pablo on 28/08/2017.
 */

public class NotificationScheduler
{

    public static void setReminder(Context context, Class<?> cls,int id, int hour, int min, String title, String text)
    {

        // cancel already scheduled reminders
        cancelReminder(context,cls,id);

        // Enable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


        Intent intent1 = new Intent(context, cls);
        intent1.putExtra("ID_NOTIF",id);
        intent1.putExtra("TIT_NOTIF",title);
        intent1.putExtra("TXT_NOTIF",text);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Calendar calendarAlarm = Calendar.getInstance();
        calendarAlarm.set(Calendar.HOUR_OF_DAY, hour);
        calendarAlarm.set(Calendar.MINUTE, min);
        calendarAlarm.set(Calendar.SECOND, 0);

        Calendar calendarActual = Calendar.getInstance();
        if(calendarAlarm.before(calendarActual))
            calendarAlarm.add(Calendar.DATE,1);

        long delay  = calendarAlarm.getTimeInMillis()- System.currentTimeMillis();
        am.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + delay,pendingIntent);

    }

    public static void cancelReminder(Context context,Class<?> cls,int id)
    {
        // Disable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void showNotification(Context context,Class<?> cls, int id, String title,String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("NotifID",id);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_notif)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);

    }

    public static void desactiveNotifState(Context context,long id){
        NotifForConsultDB nfcDB= new NotifForConsultDB(context);
        nfcDB.open();
        NotifForConsult nfc = nfcDB.getNotificacionParaNotificacionId(id);
        nfcDB.updateNotificacionParaConsulta(nfc.getId(),nfc.getConsulta(),nfc.getNotificacion(),
                nfc.getFecha(),nfc.getHora(),nfc.getTexto(),false,nfc.getTipo());
        nfcDB.close();
    }

}
