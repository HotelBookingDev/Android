package sf.hotel.com.hotel_client.view.broadcast_receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avospush.notification.NotificationCompat;

import org.json.JSONObject;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.view.activity.PushActivity;

/**
 * Created by 林其望
 * data：2016/6/27
 * email: 1105896230@qq.com
 */
public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("CustomReceiver", "isReceiver");
        TToast.showToast("CustomReceiver");
        try {
            if (intent.getAction().equals("com.pushHotel.action")) {
                JSONObject json = new JSONObject(
                        intent.getExtras().getString("com.avos.avoscloud.Data"));
                final String message = json.getString("alert");
                Intent resultIntent = new Intent(AVOSCloud.applicationContext, PushActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        AVOSCloud.applicationContext, 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                        AVOSCloud.applicationContext).setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(AVOSCloud.applicationContext.getResources()
                                .getString(R.string.app_name))
                        .setContentText(message)
                        .setTicker(message);
                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setAutoCancel(true);

                int mNotificationId = 10086;
                NotificationManager mNotifyMgr = (NotificationManager) AVOSCloud.applicationContext.getSystemService(
                        Context.NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        } catch (Exception e) {
            LogUtils.e("CustomReceiver", e.getMessage());
        }
    }
}
