package sf.hotel.com.hotel_client.view.fragment.person;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.LoginActivity;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

public class SettingFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.setting_out)
    public void logingOut(){
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @OnClick(R.id.setting_clear)
    public void settingclear(){
        HotelFileUtils.clearImageDir();
    }
}
