package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.utils.CheckUtils;
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
        view.setInvoice("875");
    }

    public void submit() {
        String inVoiceMakeUp = view.getInVoiceMakeUp();
        String inVoiceeContactInformation = view.getInVoiceeContactInformation();
        String inVoiceirecipientName = view.getInVoiceirecipientName();
        String inVoiceMaillAdress = view.getInVoiceMaillAdress();
        String inVoiceMoney = view.getInVoiceMoney();
        if (CheckUtils.isTextViewEmpty(inVoiceMakeUp) ||
                CheckUtils.isTextViewEmpty(inVoiceeContactInformation) ||
                CheckUtils.isTextViewEmpty(inVoiceirecipientName) ||
                CheckUtils.isTextViewEmpty(inVoiceMaillAdress) ||
                CheckUtils.isTextViewEmpty(inVoiceMoney)) {
            view.showViewToast("信息请填写完整");
        }
    }
}
