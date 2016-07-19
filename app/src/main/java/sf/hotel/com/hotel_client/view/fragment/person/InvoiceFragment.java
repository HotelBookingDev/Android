package sf.hotel.com.hotel_client.view.fragment.person;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IInvoiceView;
import sf.hotel.com.hotel_client.view.presenter.person.InvoicePresenter;


public class InvoiceFragment extends BaseFragment implements IInvoiceView {


    public static InvoiceFragment newInstance() {

        Bundle args = new Bundle();

        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tv_invoice_money)
    TextView TvInvoice;

    @BindView(R.id.view_title)
    HotelTitleView titleView;
    InvoicePresenter presenter;

    //    发票金额
    @BindView(R.id.ed_money)
    EditText ed_money;
    //   开票抬头
    @BindView(R.id.ed_invoice_make_up)
    EditText ed_mak_up;

    //    收件人名字
    @BindView(R.id.et_irecipient_name)
    EditText ed_irecipient_name;

    //    联系方式
    @BindView(R.id.ed_contact_information)
    EditText ed_contact_information;

    //    邮寄地址
    @BindView(R.id.ed_mail_address)
    EditText ed_mail_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);
        ButterKnife.bind(this, view);
        presenter = new InvoicePresenter(this);
        initviews();
        presenter.inindatas();
        return view;
    }

    private void initviews() {
        titleView.addLeftClick(v -> getActivity().finish());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void setInvoice(String str) {
        TvInvoice.setText(new StringBuilder().append(str).append(".00").toString());
        ed_money.setHint("填写金额需小于" + str + "元");
    }

    @Override
    public String getInVoiceMoney() {
        return ed_money.getText().toString();
    }

    @Override
    public String getInVoiceMakeUp() {
        return ed_mak_up.getText().toString();
    }

    @Override
    public String getInVoiceirecipientName() {
        return ed_irecipient_name.getText().toString();
    }

    @Override
    public String getInVoiceeContactInformation() {
        return ed_contact_information.getText().toString();
    }

    @Override
    public String getInVoiceMaillAdress() {
        return ed_mail_address.getText().toString();
    }

    @OnClick({R.id.submit})
    public void OnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.submit:
                presenter.submit();
                break;
        }
    }
}
