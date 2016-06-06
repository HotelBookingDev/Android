package sf.hotel.com.data.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class LoadingDialogUtils {
    private static ProgressDialog dialog;

    /**
     * 默认
     * @param context
     * @param message
     */
    public static void showProgress(Context context, CharSequence message){
        showProgress(context, null, message, false, false);
    }

    /**
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showProgress(Context context, CharSequence title, CharSequence message){
        showProgress(context, title, message, false, false);
    }

    /**
     * 显示进度条
     *
     * @param context
     *            环境
     * @param title
     *            标题
     * @param message
     *            信息
     * @param indeterminate
     *            确定性
     * @param cancelable
     *            可撤销
     * @return
     */
    public static void showProgress(Context context, CharSequence title, CharSequence message,
                                    boolean indeterminate, boolean cancelable) {
        if (dialog == null){
            dialog = new ProgressDialog(context);
        }
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(indeterminate);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.show();
    }

    /**
     * 显示dialog
     *
     * @param context
     *            环境
     * @param strTitle
     *            标题
     * @param strText
     *            内容
     * @param icon
     *            图标
     */
    public static void showDialog(Context context, String strTitle, String strText, int icon) {
        AlertDialog.Builder tDialog = new AlertDialog.Builder(context);
        tDialog.setIcon(icon);
        tDialog.setTitle(strTitle);
        tDialog.setMessage(strText);
        tDialog.setPositiveButton(android.R.string.ok, null);
        tDialog.show();
    }

    public static void dismissDialog(){
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
    }
}
