package sf.hotel.com.data.interfaceeneity.person;

import sf.hotel.com.data.config.EntityContext;

/**
 * Created by "林其望".
 * DATE: 2016:07:25:20:08
 * email:1105896230@qq.com
 */

public class IPersonImp implements IPerson {

    @Override
    public long getPoints() {
        if (EntityContext.getInstance().getmCurrentUser() == null)
            return 0;
        return EntityContext.getInstance().getmCurrentUser().getPoint();
    }
}
