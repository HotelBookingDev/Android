package sf.hotel.com.hotel_client.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sf.hotel.com.hotel_client.view.fragment.person.orders.UsedOrder;

/**
 * Created by YoKeyword
 * data：16/6/5
 */
public class OrderPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab =null;

    public OrderPagerFragmentAdapter(FragmentManager fm, String[] mTab) {
        super(fm);
        this.mTab = mTab;
    }

    @Override
    public Fragment getItem(int position) {
        return UsedOrder.newInstance();
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
