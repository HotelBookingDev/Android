package sf.hotel.com.data.config;

import dagger.Component;
import sf.hotel.com.data.datasource.UserDao;

/**
 * Created by FMT on 2016/6/3:18:20
 * EMAILE 1105896230@qq.com.
 */
@Component(modules = InteractorModule.class)
public interface DataConmponent {
    UserDao userDao();
}
