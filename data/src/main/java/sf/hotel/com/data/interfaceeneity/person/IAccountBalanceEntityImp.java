package sf.hotel.com.data.interfaceeneity.person;

import sf.hotel.com.data.config.EntityContext;

/**
 * Created by "林其望".
 * DATE: 2016:07:25:20:13
 * email:1105896230@qq.com
 */

public class IAccountBalanceEntityImp implements IAccountBalanceEntity {
    @Override
    public long getPoint() {
        return EntityContext.getInstance().getmCurrentUser().getPoint();
    }
}
