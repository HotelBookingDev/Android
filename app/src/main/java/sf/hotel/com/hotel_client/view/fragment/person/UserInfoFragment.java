package sf.hotel.com.hotel_client.view.fragment.person;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.register.LoginActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.custom.PersonalItemView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserInfoView;
import sf.hotel.com.hotel_client.view.presenter.person.UserInfoPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment implements IUserInfoView {

    UserInfoPresenter mUserInfoPresenter;
    @BindView(R.id.piv_use)
    PersonalItemView mUsetPhone;
    @BindView(R.id.view_title)
    HotelTitleView mViewTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        mUserInfoPresenter = new UserInfoPresenter(this);
        mViewTitle.addLeftClick(v -> getActivity().finish());
        return view;
    }


    @Override
    public void setUserName(String name) {
        if (!TextUtils.isEmpty(name)) {
            mUsetPhone.setRightText(name);
        }
    }

    public void logOutToLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserInfoPresenter.destroy();
    }

    public static UserInfoFragment newInstance() {
        Bundle args = new Bundle();
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({
            R.id.piv_use, R.id.piv_pay_pwd, R.id.setting_out
    })
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.setting_out:
                loginOut();
                break;
        }
    }

    public void loginOut() {
        //清空本地设置
        mUserInfoPresenter.loginOut();
    }
}
