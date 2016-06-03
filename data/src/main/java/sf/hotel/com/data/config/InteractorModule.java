package sf.hotel.com.data.config;

import dagger.Module;
import dagger.Provides;
import sf.hotel.com.data.datasource.UserDao;

/**
 * Created by FMT on 2016/6/3:18:21
 * EMAILE 1105896230@qq.com.
 */
@Module
public class InteractorModule {
    @Provides
    public UserDao provideUserDao() {
        return new UserDao();
    }
}
