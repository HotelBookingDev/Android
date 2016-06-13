package sf.hotel.com.hotel_client.utils.imageconfig.glidebuilds;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by FMT on 2016/6/13:14:25
 * EMAILE 1105896230@qq.com.
 */
public enum GlideBuildFactory {
    INSTACNE;

    public static final int SIMPLEBUILD = 0x1;
    public static final int CLICLEBUISD = 0x2;
    HashMap map = new HashMap<Integer, GlideBuild>();

    public GlideBuild getBuild(int type, Context context) {
        if (map.get(type) != null) return (GlideBuild) map.get(type);
        if (type == SIMPLEBUILD) {
            map.put(SIMPLEBUILD, new SimpleBuild());
        } else if (type == CLICLEBUISD) {
            map.put(CLICLEBUISD, new CircleBuild(context));
        }
        return (GlideBuild) map.get(type);
    }
}
