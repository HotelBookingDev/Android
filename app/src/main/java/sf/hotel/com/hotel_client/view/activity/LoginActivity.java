package sf.hotel.com.hotel_client.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;
import sf.hotel.com.hotel_client.view.persenter.ILoginPersenter;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.edit_name)
    EditText mEditName;
    @BindView(R.id.edit_pw)
    EditText mEditPw;
    ILoginPersenter mILoginPersenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mILoginPersenter = new ILoginPersenter(this);
    }

    @Override
    public String getUserName() {
        return mEditName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditPw.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEditName.setText("");
    }

    @Override
    public void clearPassword() {
        mEditPw.setText("");
    }

    @Override
    public void showLoading() {
        Log.e("tte", "showLoading");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailedError() {

    }

    @Override
    @OnClick(R.id.loginbtn)
    public void login() {
        mILoginPersenter.login();
    }

    @Override
    public EditText getEditName() {
        return mEditName;
    }

    @Override
    public void success() {

    }

    @Override
    public void error() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mILoginPersenter.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mILoginPersenter.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mILoginPersenter.resume();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
