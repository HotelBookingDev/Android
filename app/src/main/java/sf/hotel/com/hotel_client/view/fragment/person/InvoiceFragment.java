package sf.hotel.com.hotel_client.view.fragment.person;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        TvInvoice.setText(str);
    }
}
