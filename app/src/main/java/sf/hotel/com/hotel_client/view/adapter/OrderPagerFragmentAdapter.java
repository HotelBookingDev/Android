package sf.hotel.com.hotel_client.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sf.hotel.com.hotel_client.view.fragment.person.orders.UserdOrder;

/**
 * Created by YoKeyword
 * data：16/6/5
 */
public class OrderPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab = new String[]{"未消费", "已消费", "已退款", "待付款"};

    public OrderPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return UserdOrder.newInstance();
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
