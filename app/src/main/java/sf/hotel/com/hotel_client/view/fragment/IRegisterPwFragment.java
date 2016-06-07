package sf.hotel.com.hotel_client.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.interfaceview.IRegiserPwView;
import sf.hotel.com.hotel_client.view.presenter.IRegisterPwPresenter;

/**
 * Created by FMT on 2016/6/6:10:15
 * EMAILE 1105896230@qq.com.
 */
public class IRegisterPwFragment extends BaseFragment implements IRegiserPwView {

    IRegisterPwPresenter mIRegisterPwPersenter;
    @BindView(R.id.register_phone)
    EditText mEdPhone;
    @BindView(R.id.register_send)
    Button mSendBtn;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                    String num = (String) msg.obj;
                    mSendBtn.setText(num);
                    break;
                case 2:
                    mSendBtn.setText(R.string.get_code);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registe_pwr, container, false);
        ButterKnife.bind(this, view);
        mIRegisterPwPersenter = new IRegisterPwPresenter(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public String getPhoneNum() {
        return mEdPhone.getText().toString();
    }

    @OnClick(R.id.register_send)
    @Override
    public void sendInvitationCode() {
        mIRegisterPwPersenter.sendMessage();
    }

    @Override
    public void setInvitationCodeNum(String num) {
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.obj = num;
        mHandler.sendMessage(message);
    }

    @Override
    public void reset() {
        mHandler.sendEmptyMessage(2);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
