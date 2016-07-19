package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.hotel_client.view.interfaceview.person.IInvoiceView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by "林其望".
 * DATE: 2016:07:19:20:14
 * email:1105896230@qq.com
 */

public class InvoicePresenter extends SuperPresenter {
    IInvoiceView view;

    public InvoicePresenter(IInvoiceView view) {
        this.view = view;
    }

    public void inindatas() {
        view.setInvoice("875.00");
    }
}
