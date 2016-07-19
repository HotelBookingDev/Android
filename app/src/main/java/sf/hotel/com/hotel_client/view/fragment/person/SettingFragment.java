package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.custom.ToggleButton;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISettingView;
import sf.hotel.com.hotel_client.view.presenter.person.SettingPresenter;

public class SettingFragment extends BaseFragment implements ISettingView {

    SettingPresenter settingPresenter;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.tb_accept_msg)
    ToggleButton mTbAcceptMsg;

    @BindView(R.id.view_title)
    HotelTitleView view_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        settingPresenter = new SettingPresenter(this);
        return view;
    }

    private void initView() {
        setSwitch(PreferencesUtils.getAcceptMeg(getActivity()), mTbAcceptMsg);
        view_title.addLeftClick(v -> {
            getActivity().finish();
        });
    }

    @Override
    public void setSwitch(boolean isOpen, ToggleButton viewSwitch) {
        if (isOpen) {
            viewSwitch.setToggleOn(true);
        } else {
            viewSwitch.setToggleOff(true);
        }
    }


    @Override
    public ToggleButton getAcceptMsg() {
        return mTbAcceptMsg;
    }


    public void sendMsg() {
        settingPresenter.changeAcceptMsg();
    }

    @OnClick({
            R.id.tb_accept_msg,
            R.id.piv_service,
            R.id.piv_question,
            R.id.piv_feed_back
    })
    void onClick(View view) {
        int id = view.getId();
        //修改是否接受消息
        if (id == R.id.tb_accept_msg) {
            sendMsg();
        } else switch (id) {
            case R.id.piv_service:
                break;
            case R.id.piv_question:
                break;
            case R.id.piv_feed_back:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        settingPresenter.destroy();
    }
}
