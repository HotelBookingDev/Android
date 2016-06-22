package sf.hotel.com.hotel_client.view.fragment.person;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserInfoView;
import sf.hotel.com.hotel_client.view.presenter.person.UserInfoPersenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment implements IUserInfoView {

    private static final int REQUEST_CODE_PICK_IMAGE = 0x1;
    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    UserInfoPersenter mUserInfoPersenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        mUserInfoPersenter = new UserInfoPersenter(this);
        return view;
    }

    @OnClick(R.id.iv_avatar)
    public void upAvatar() {
        getImageFromAlbum();
    }

    @Override
    public ImageView getAvatar() {
        return mAvatar;
    }

    @Override
    public Context getBottomContext() {
        return getContext();
    }

    private void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                mUserInfoPersenter.upFile(uri);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserInfoPersenter.destroy();
    }
}
