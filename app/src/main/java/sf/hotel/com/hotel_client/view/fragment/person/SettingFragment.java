package sf.hotel.com.hotel_client.view.fragment.person;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sf.hotel.com.data.config.HotelConstant;
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.HotelApplication;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.LoginActivity;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

public class SettingFragment extends BaseFragment {

    @BindView(R.id.setting_clear)
    FancyButton mClearBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        setBtnClearText();
    }

    @OnClick(R.id.setting_out)
    public void logingOut() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.setting_clear)
    public void settingclear() {
        HotelFileUtils.clearImageDir();
        setBtnClearText();
    }

    private void setBtnClearText() {
        rx.Observable.just(mClearBtn).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(fancyButton -> HotelFileUtils
                .getSize(new File(HotelConstant.TEMP_IMG_DIR))).filter(s -> s != null).subscribe
                (s -> {
                    mClearBtn.setText(mClearBtn.getText() + "(" + s + ")");
                });
    }
}
