package sf.hotel.com.hotel_client.view.presenter.person;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.interfaceeneity.IUserInfoEntity;
import sf.hotel.com.data.interfaceeneity.IUserInfoEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.data.utils.QNUpFileUtils;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserInfoView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望 on 2016/6/22.
 */
public class UserInfoPersenter extends SuperPresenter {
    private static final java.lang.String TAG = "UserInfoPersenter";
    IUserInfoView mIUserInfoView;
    private IUserInfoEntity mIUserInfoEntity;
    private CompositeSubscription mCompositeSubscription;

    public UserInfoPersenter(IUserInfoView mIUserInfoView) {
        this.mIUserInfoView = mIUserInfoView;
        mIUserInfoEntity = new IUserInfoEntityImp();
        mCompositeSubscription = new CompositeSubscription();
        initAvater(mIUserInfoView.getBottomContext(), mIUserInfoView.getAvatar());
    }

    @Override

    public void destroy() {
        mCompositeSubscription.unsubscribe();
    }

    public void upFile(Uri uri) {
        Subscription subscribe = Observable.just(uri)
                .map(uri1 -> getFile(uri1, mIUserInfoView.getBottomContext()))
                .filter(file -> {
                    if (file == null || file.length() == 0) return false;
                    return true;
                })
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
        }, throwable -> {
            handlingException(throwable);
        });
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
