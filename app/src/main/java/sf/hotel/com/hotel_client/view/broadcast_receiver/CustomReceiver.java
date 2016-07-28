package sf.hotel.com.hotel_client.view.broadcast_receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avospush.notification.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.PushBaseActivity;
import sf.hotel.com.hotel_client.view.activity.person.OrderActivity;
import sf.hotel.com.hotel_client.view.presenter.CustomReceiverPresenter;

/**
 * Created by 林其望
 * data：2016/6/27
 * email: 1105896230@qq.com
 */
public class CustomReceiver extends BroadcastReceiver {
    CustomReceiverPresenter presenter;

    @Override
    public void onReceive(Context context, Intent intent) {
        presenter = new CustomReceiverPresenter();
        try {
            if (intent.getAction().equals("com.pushHotel.action")) {
                JSONObject json = new JSONObject(
                        intent.getExtras().getString("com.avos.avoscloud.Data"));
                final String message = json.getString("alert");
                String type = json.getString("type");
                buildNotification(getIntent(type, json, context), message);
            }
        } catch (Exception e) {
            LogUtils.e("CustomReceiver", e.getMessage());
        }
    }

    private Intent getIntent(String type, JSONObject jsonObject, Context context) {
        Intent intent = null;
        //订单类型
        if (type.equals("1")) {
            intent = new Intent(AVOSCloud.applicationContext, OrderActivity.class);
            String order = null;
            try {
                order = jsonObject.getString("order");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(order)) {
                presenter.updateOrder(order, context);
            }
        }
        return intent;
    }

    private void buildNotification(Intent intent, String message) {
        if (intent == null) return;
        PendingIntent pendingIntent = PendingIntent.getActivity(AVOSCloud.applicationContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                AVOSCloud.applicationContext).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(
                        AVOSCloud.applicationContext.getResources().getString(R.string.app_name))
                .setContentText(message)
                .setTicker(message);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        int mNotificationId = 10086;
        NotificationManager mNotifyMgr = (NotificationManager) AVOSCloud.applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
