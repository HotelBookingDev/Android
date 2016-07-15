package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IAccountBalanceFragmentView;
import sf.hotel.com.hotel_client.view.presenter.person.AccountBalancePresenter;

public class AccountBalanceFragmentView extends BaseFragment
        implements IAccountBalanceFragmentView {

    public static AccountBalanceFragmentView newInstance() {

        Bundle args = new Bundle();

        AccountBalanceFragmentView fragment = new AccountBalanceFragmentView();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.view_title)
    HotelTitleView mViewTitle;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;

    AccountBalancePresenter mAccountPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_balance, container, false);
        ButterKnife.bind(this, view);
        initView();
        mAccountPresenter = new AccountBalancePresenter(this);
        showViews();
        return view;
    }

    private void initView() {
        mViewTitle.addLeftClick(v -> pop());
    }

    //    充值的方法
    @Override
    public void Recharge() {

    }

    @Override
    public void showMoney(String money) {
        tvAccountBalance.setText(money);
    }

    @Override
    public void showViews() {
        mAccountPresenter.initViews();
    }

    @OnClick({R.id.add_money})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.add_money:
                Recharge();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAccountPresenter.destroy();
    }
}
