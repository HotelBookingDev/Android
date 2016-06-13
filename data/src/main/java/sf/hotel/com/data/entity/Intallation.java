package sf.hotel.com.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FMT on 2016/6/13:09:44
 * EMAILE 1105896230@qq.com.
 */
public class Intallation {
    private String deviceType;
    private String installationId;

    public Intallation(String deviceType, String intallationId) {
        this.deviceType = deviceType;
        this.installationId = intallationId;
    }

    private List<String> chanels = new ArrayList<>();
    private String deviceToken;
}
