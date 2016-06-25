package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IChangePwdView;
import sf.hotel.com.hotel_client.view.presenter.person.ChangePersenter;

public class ChangePwdFragment extends BaseFragment implements IChangePwdView {
    private ChangePersenter mChangePersenter;

    public static ChangePwdFragment newInstance() {

        Bundle args = new Bundle();

        ChangePwdFragment fragment = new ChangePwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.et_old)
    EditText mOldEditTxt;
    @BindView(R.id.et_new)
    EditText mNewEditTxt;
    @BindView(R.id.et_determine_confirm)
    EditText mConfirmPwdEditTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pwd, container, false);
        ButterKnife.bind(this, view);
        mChangePersenter = new ChangePersenter(this);
        return view;
    }

    @OnClick({R.id.submit_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.submit_btn) {
            mChangePersenter.changePwd();
        }
    }

    @Override
    public EditText getOldPwd() {
        return mOldEditTxt;
    }

    @Override
    public EditText getNewPwd() {
        return mNewEditTxt;
    }

    @Override
    public EditText getConfimPwd() {
        return mConfirmPwdEditTxt;
    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }
}
