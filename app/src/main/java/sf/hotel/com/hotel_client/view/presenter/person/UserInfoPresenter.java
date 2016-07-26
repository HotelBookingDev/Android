package sf.hotel.com.hotel_client.view.presenter.person;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import rx.Observable;
import rx.Subscription;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.interfaceeneity.person.IUserInfoEntity;
import sf.hotel.com.data.interfaceeneity.person.IUserInfoEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.data.utils.QNUpFileUtils;
import sf.hotel.com.hotel_client.utils.AndroidUtils;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserInfoView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class UserInfoPresenter extends SuperPresenter {
    IUserInfoView mIUserInfoView;
    private IUserInfoEntity mIUserInfoEntity;

    public UserInfoPresenter(IUserInfoView mIUserInfoView) {
        super();
        this.mIUserInfoView = mIUserInfoView;
        mIUserInfoEntity = new IUserInfoEntityImp();
        initAttribute();
    }

    //初始化界面
    private void initAttribute() {
        Subscription subscribe = Observable.just(mIUserInfoEntity.getUserEntity())
                .filter(userEntity -> userEntity == null ? Boolean.FALSE : Boolean.TRUE)
                .subscribe(userEntity -> {
                    mIUserInfoView.setUserName(userEntity.getPhoneNumber() + "");
                });
        addSubsrcicitpition(subscribe);
    }


    @Override
    public void handlingException(Throwable e) {
        if (e instanceof APIException) {

        } else {
            mIUserInfoView.showViewToast(e.getMessage());
        }
    }

    public void loginOut() {
        invalidate();
        mIUserInfoView.logOutToLoginActivity();
    }

    private void invalidate() {
        PreferencesUtils.saveToken(mIUserInfoView.getBottomContext(), null);
        PreferencesUtils.saveAvatar(mIUserInfoView.getBottomContext(), null);
        PreferencesUtils.saveInstallationId(mIUserInfoView.getBottomContext(), null);
        PreferencesUtils.savePhone(mIUserInfoView.getBottomContext(), null);
        PreferencesUtils.saveLogin(mIUserInfoView.getBottomContext(), false);
        PreferencesUtils.saveUserId(mIUserInfoView.getBottomContext(), -1);
        PreferencesUtils.saveHotelResult(mIUserInfoView.getBottomContext(), null);
    }
    //    private String getPhone(long phone) {
//        final String[] showPhone = {null};
//        Subscription subscribe = Observable.just(phone)
//                .map(aLong -> Long.toString(phone))
//                .filter(s -> s == null ? Boolean.FALSE : Boolean.TRUE)
//                .filter(s -> s.length() == 11 ? Boolean.TRUE : Boolean.FALSE)
//                .map(this::getString)
//                .subscribe(s -> {
//                    showPhone[0] = s;
//                });
//        addSubsrcicitpition(subscribe);
//        return showPhone[0];
//    }
//
//    //将手机号进行打*处理
//    private String getString(String phone) {
//        //需要判断当前手机语言是否是中文来决定区号
//        String str = "+86";
//        if (!AndroidUtils.isZh(mIUserInfoView.getBottomContext())) {
//            str = "";
//        }
//        return str +
//                phone.substring(0, 3) +
//                "****" +
//                phone.substring(7, 11);
//    }
//
//    public void upFile(Uri uri) {
//        Subscription subscribe = Observable.just(uri)
//                .map(uri1 -> getFile(uri1, mIUserInfoView.getBottomContext()))
//                .filter(file -> !(file == null || file.length() == 0))
//                .subscribe(new SimpleSubscriber<File>(mIUserInfoView.getBottomContext()) {
//                    @Override
//                    public void onNext(File file) {
//                        super.onNext(file);
//                        upFile(file);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        super.onCompleted();
//                    }
//                });
//        addSubsrcicitpition(subscribe);
//    }
//
//    private void upFile(File file) {
//        Subscription subscribe = mIUserInfoEntity.getToken().subscribe(tokenResult -> {
//            QNUpFileUtils.upFileByQN(file, tokenResult);
//        }, this::handlingException);
//        addSubsrcicitpition(subscribe);
//    }
//
//    private File getFile(Uri uri, Context context) {
//        String path = HotelFileUtils.getPath(context, uri);
//        File file = null;
//        if (!TextUtils.isEmpty(path)) {
//            file = new File(path);
//        }
//        return file;
//    }
}
