package sf.hotel.com.hotel_client.view.presenter.person;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.interfaceeneity.person.IUserInfoEntity;
import sf.hotel.com.data.interfaceeneity.person.IUserInfoEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.HotelFileUtils;
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
    private CompositeSubscription mCompositeSubscription;

    public UserInfoPresenter(IUserInfoView mIUserInfoView) {
        this.mIUserInfoView = mIUserInfoView;
        mIUserInfoEntity = new IUserInfoEntityImp();
        mCompositeSubscription = new CompositeSubscription();
        initAvater(mIUserInfoView.getBottomContext(), mIUserInfoView.getAvatar());
        initAttribute();
    }

    //初始化界面
    private void initAttribute() {
        Subscription subscribe = Observable.just(EntityContext.getInstance().getmCurrentUser())
                .filter(userEntity -> userEntity == null ? Boolean.FALSE : Boolean.TRUE)
                .subscribe(userEntity -> {
                    mIUserInfoView.setUserName(userEntity.getFullname());
                    mIUserInfoView.setUserPwd(getPhone(userEntity.getPhoneNumber()));
                });
        mCompositeSubscription.add(subscribe);
    }

    private String getPhone(long phone) {
        final String[] showPhone = {null};
        Subscription subscribe = Observable.just(phone)
                .map(aLong -> Long.toString(phone))
                .filter(s -> s == null ? Boolean.FALSE : Boolean.TRUE)
                .filter(s -> s.length() == 11 ? Boolean.TRUE : Boolean.FALSE)
                .map(this::getString)
                .subscribe(s -> {
                    showPhone[0] = s;
                });
        mCompositeSubscription.add(subscribe);
        return showPhone[0];
    }

    //将手机号进行打*处理
    private String getString(String phone) {
        //需要判断当前手机语言是否是中文来决定区号
        String str = "+86";
        if (!AndroidUtils.isZh(mIUserInfoView.getBottomContext())) {
            str = "";
        }
        return str +
                phone.substring(0, 3) +
                "****" +
                phone.substring(7, 11);
    }

    @Override

    public void destroy() {
        mCompositeSubscription.unsubscribe();
    }

    public void upFile(Uri uri) {
        Subscription subscribe = Observable.just(uri)
                .map(uri1 -> getFile(uri1, mIUserInfoView.getBottomContext()))
                .filter(file -> !(file == null || file.length() == 0))
                .subscribe(new SimpleSubscriber<File>(mIUserInfoView.getBottomContext()) {
                    @Override
                    public void onNext(File file) {
                        super.onNext(file);
                        upFile(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void upFile(File file) {
        Subscription subscribe = mIUserInfoEntity.getToken().subscribe(tokenResult -> {
            QNUpFileUtils.upFileByQN(file, tokenResult);
        }, this::handlingException);
        mCompositeSubscription.add(subscribe);
    }

    private File getFile(Uri uri, Context context) {
        String path = HotelFileUtils.getPath(context, uri);
        File file = null;
        if (!TextUtils.isEmpty(path)) {
            file = new File(path);
        }
        return file;
    }

    @Override
    public void handlingException(Throwable e) {
        if (e instanceof APIException) {

        } else {
            mIUserInfoView.showViewToast(e.getMessage());
        }
    }
}
