package sf.hotel.com.hotel_client.utils;

import com.github.johnpersano.supertoasts.SuperToast;

import sf.hotel.com.hotel_client.HotelApplication;

/**
 * Created by FMT on 2016/6/3:12:28
 * EMAILE 1105896230@qq.com.
 */
public final class TToast {
    private TToast() {
        throw new RuntimeException("can not have examples");
    }

    private static final SuperToast superToast;

    static {
        superToast = new SuperToast(HotelApplication.context);
        superToast.setAnimations(SuperToast.Animations.FLYIN);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setBackground(SuperToast.Background.RED);
        superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
    }

    public static void showToast(String message) {
        superToast.setText(message);
        superToast.show();
    }
}
