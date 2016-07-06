package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.DensityUtils;
import sf.hotel.com.hotel_client.utils.LBSUtils;
import sf.hotel.com.hotel_client.view.adapter.DialogBedAdapter;
import sf.hotel.com.hotel_client.view.adapter.LocalImageHodlerView;
import sf.hotel.com.hotel_client.view.custom.HideTitle;
import sf.hotel.com.hotel_client.view.custom.NoScrollView;
import sf.hotel.com.hotel_client.view.custom.PersonalItemView;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IRoomView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IRoomPresenter;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/16.
 */
public class RoomFragment extends BaseFragment implements IRoomView {

    private static Bundle args;

    private HotelsBean hotelsBean;

    private List<String> mImageList;

    IRoomPresenter mIRoomPresenter;

    @BindView(R.id.frame_room_banner)
    ConvenientBanner convenientBanner;

    @BindView(R.id.fragment_room_content)
    TextView mRoomContent;

    @BindView(R.id.frag_room_search)
    FancyButton mBtnSearch;

    DialogPlus dialogPlus, phoneDialog;

    @BindView(R.id.frag_room_title)
    HideTitle mTitle;

    @BindView(R.id.frag_room_scrollview)
    NoScrollView mNoScrollView;


    @BindView(R.id.fragment_room_phone)
    PersonalItemView mPhone;

    @BindView(R.id.fragment_room_location)
    PersonalItemView mLocation;


    public static RoomFragment newInstance(Bundle bundle) {

        if (bundle != null) {
            args = bundle;
        } else {
            args = new Bundle();
        }
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RoomFragment newInstance() {
        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        mIRoomPresenter = new IRoomPresenter(this);
        ButterKnife.bind(this, view);
        hotelsBean = args.getParcelable("room");
        initBanner();
       // initScrollView();
        initTitle();


        return view;
    }


    private void initBanner() {

        mImageList = new ArrayList<>();

        mImageList.add("http://f.hiphotos.baidu.com/image/h%3D300/sign=e50211178e18367ab28979dd1e738b68/0b46f21fbe096b63a377826e04338744ebf8aca6.jpg");
        mImageList.add("http://img0.imgtn.bdimg.com/it/u=2460737275,599413823&fm=23&gp=0.jpg");
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHodlerView>() {
            @Override
            public LocalImageHodlerView createHolder() {
                return new LocalImageHodlerView();
            }
        }, mImageList)
                .setPageIndicator(
                        new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    private void initTitle() {
        int hideHeight =  DensityUtils.dp2px(getBottomContext(), 200);
        mNoScrollView.setOnScrollListener(new NoScrollView.onScrollListener() {
            @Override
            public void onScroll(View view, int x, int y, int oldX, int oldY) {

                int curr = y - 1500;

                if(curr < hideHeight){
                    float alpha = curr / (float)hideHeight;
                    if (alpha < 0)
                        alpha = 0;
                    if (alpha > 1)
                        alpha = 1;
                    mTitle.setViewAlpha(alpha);
                }
            }
        });

        mTitle.addLeftViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault()
                        .post(MessageFactory.createRoomMessage(RoomMessage.ACTIVITY_BACK));
            }
        });
    }

    @OnClick({R.id.frag_room_search,
            R.id.fragment_room_phone,
            R.id.fragment_room_location})
    public void onSearchClick(View v) {

        switch (v.getId()){
            case R.id.frag_room_search:
                if (dialogPlus == null) {
                    dialogPlus = DialogPlus.newDialog(getBottomContext())
                            .setContentHolder(new ListHolder())
                            .setCancelable(true)
                            .setGravity(Gravity.BOTTOM)
                            .setFooter(R.layout.footer_bed)
                            .setHeader(R.layout.header_bed)
                            .setAdapter(new DialogBedAdapter(getBottomContext()))
                            .setOnItemClickListener((dialog, item, view, position) -> {

                            })
                            .setOnDismissListener(dialog -> {

                            })
                            .setOnCancelListener(dialog -> {

                            })
                            .setExpanded(true)
                            .create();
                }
                dialogPlus.show();
                break;

            case R.id.fragment_room_phone:

                if (phoneDialog == null){
                    View phoneView = LayoutInflater.from(getBottomContext()).inflate(R.layout.dialog_phone, null, false);
                    ViewHolder holder = new ViewHolder(phoneView);
                    phoneDialog = DialogPlus.newDialog(getBottomContext())
                            .setContentHolder(holder)
                            .setCancelable(true)
                            .setGravity(Gravity.CENTER)
                            .create();
                }
                phoneDialog.show();
                break;
            case R.id.fragment_room_location:

                if (!LBSUtils.startLBS(getActivity(), "", "", "")){
                    showViewToast("没有安装百度地图");
                }

                break;

        }

    }


    @Override
    public Context getBottomContext() {
        return getActivity();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
