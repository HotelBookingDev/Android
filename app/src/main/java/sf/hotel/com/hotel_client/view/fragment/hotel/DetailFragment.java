package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.IDetailView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IDetailPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/14.
 */
public class DetailFragment extends BaseFragment implements IDetailView{

    IDetailPresenter mIDetailPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mIDetailPresenter = new IDetailPresenter(this);
        return view;
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {

    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }
}
