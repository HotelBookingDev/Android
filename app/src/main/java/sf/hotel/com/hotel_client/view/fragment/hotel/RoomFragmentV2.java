package sf.hotel.com.hotel_client.view.fragment.hotel;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.hotel.Images;
import sf.hotel.com.data.entity.netresult.hotel.room.HotelBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomResult;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.StartActivityUtils;
import sf.hotel.com.hotel_client.view.activity.hotel.BookingActivity;
import sf.hotel.com.hotel_client.view.adapter.LocalImageHodlerView;
import sf.hotel.com.hotel_client.view.adapter.RoomExpandListAdapter;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.custom.PersonalItemView;
import sf.hotel.com.hotel_client.view.custom.PriceText;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IRoomView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IRoomPresenter;


/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/16.
 */

public class RoomFragmentV2 extends BaseFragment implements IRoomView {

    private static Bundle args;

    private int hotelId;
    private RoomResult hotelsBean;

    private List<String> mImageList;
    IRoomPresenter mIRoomPresenter;
    ConvenientBanner convenientBanner;
    TextView mRoomContent;
    DialogPlus dialogPlus, phoneDialog;

    PersonalItemView mPhone;
    PersonalItemView mLocation;

    @BindView(R.id.fragment_room_listview)
    ExpandableListView mListView;

    RoomExpandListAdapter mAdapter;

    TextView phoneText;
    FancyButton phoneCancel;
    FancyButton phoneSubmit;

    SearchItem searchItem;

    PriceText priceText;

    @BindView(R.id.fragment_room_v2_title)
    HotelTitleView mTitle;

    public static RoomFragmentV2 newInstance(Bundle bundle) {

        if (bundle != null) {
            args = bundle;
        } else {
            args = new Bundle();
        }
        RoomFragmentV2 fragment = new RoomFragmentV2();
        fragment.setArguments(args);
        return fragment;
    }

    public static RoomFragmentV2 newInstance() {
        Bundle args = new Bundle();
        RoomFragmentV2 fragment = new RoomFragmentV2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_room_v2, container, false);
        mIRoomPresenter = new IRoomPresenter(this);
        ButterKnife.bind(this, view);

        mTitle.addLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(MessageFactory.createRoomMessage(RoomMessage.ACTIVITY_BACK));
            }
        });

        initRecycler();
        initDate(args);
        return view;
    }

    private void initRecycler() {
        View header = LayoutInflater.from(getBottomContext()).inflate(R.layout.header_room_v2, null);
        initHeader(header);
        mAdapter = new RoomExpandListAdapter(getBottomContext());
        mAdapter.setChildSubmitClickListener(new RoomExpandListAdapter.OnChildSubmitClickListener() {
            @Override
            public void onChildSubmitClick(int groupPos, int childPos, float point, float price) {
                showBooking(groupPos, childPos, point, price);
            }
        });

        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                mAdapter.setShowGroupPos(groupPosition);
                for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        mListView.collapseGroup(i);
                    }
                }
            }
        });

        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                if (mAdapter.getShowGroupPos() == groupPosition){
                    mAdapter.setShowGroupPos(-1);
                }
            }
        });

        mListView.addHeaderView(header);
        mListView.setAdapter(mAdapter);
    }


    private void initDate(Bundle args) {
        hotelId = args.getInt("room");
        mIRoomPresenter.callHotelBean();
        mIRoomPresenter.getSearchItem();
    }

    private void initHeader(View view) {
        mPhone = (PersonalItemView) view.findViewById(R.id.fragment_room_v2_phone);
        mLocation = (PersonalItemView) view.findViewById(R.id.fragment_room_v2_location);
        priceText = (PriceText) view.findViewById(R.id.zoom_room_price);
        mRoomContent = (TextView) view.findViewById(R.id.fragment_room_v2_content);

        convenientBanner = (ConvenientBanner) view.findViewById(R.id.frame_room_banner);

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneDialog == null) {
                    phoneDialog = DialogPlus.newDialog(getBottomContext())
                            .setContentHolder(new ViewHolder(R.layout.dialog_phone))
                            .setCancelable(true)
                            .setGravity(Gravity.CENTER)
                            .create();
                    phoneCancel = (FancyButton) phoneDialog.findViewById(R.id.dialog_cancel);
                    phoneSubmit = (FancyButton) phoneDialog.findViewById(R.id.dialog_submit);
                    phoneText = (TextView) phoneDialog.findViewById(R.id.dialog_phone_text);
                    phoneText.setText(mPhone.getText());

                    phoneCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (phoneDialog != null && phoneDialog.isShowing()) {
                                phoneDialog.onBackPressed(phoneDialog);
                            }
                        }
                    });
                    phoneSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (phoneDialog != null && phoneDialog.isShowing()) {
                                phoneDialog.dismiss();
                            }
                            if (StartActivityUtils.startPhone(getActivity(), phoneText.getText().toString())){
                                LogUtils.d("没有拨打电话功能");
                            }

                        }
                    });
                }
                phoneDialog.show();
            }
        });

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StartActivityUtils.startLBS(getActivity(), "", "", "")) {
                    showViewToast("没有安装百度地图");
                }
            }
        });
        initBanner();
    }



    private void initBanner() {

        mImageList = new ArrayList<>();
        mImageList.add(
                "http://f.hiphotos.baidu.com/image/h%3D300/sign=e50211178e18367ab28979dd1e738b68/0b46f21fbe096b63a377826e04338744ebf8aca6.jpg");
        mImageList.add("http://img0.imgtn.bdimg.com/it/u=2460737275,599413823&fm=23&gp=0.jpg");

        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHodlerView>() {
            @Override
            public LocalImageHodlerView createHolder() {
                return new LocalImageHodlerView();
            }
        }, mImageList)
                .setPageIndicator(
                        new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_LEFT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                })

            ;
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


    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (dialogPlus != null) {
            if (dialogPlus.isShowing()) {
                dialogPlus.dismiss();
            }
            dialogPlus = null;
        }

        if (phoneDialog != null) {
            if (phoneDialog.isShowing()) {
                phoneDialog.dismiss();
            }
            phoneDialog = null;
        }
    }

    public void showBooking(int groupPos, int childPos,float point,float price){
        if (mIRoomPresenter.checkIsLogin()){

            mIRoomPresenter.saveBooking(groupPos, childPos, point, price);
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            intent.putExtra("action", "Room");
            startActivity(intent);
        } else {
            RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.SHOW_LOGIN));
        }
    }

    public int getHotelId() {
        return hotelId;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIRoomPresenter.destroy();
    }

    public void setImageList(List<Images> list){
        if (list != null && list.size() > 0){
            mImageList.clear();
            for (Images images : list){
                mImageList.add(images.getImg());
            }

            convenientBanner.notifyDataSetChanged();
        }
    }

    public void setRoomContentText(String text) {
        mRoomContent.setText(text);
    }


    @Override
    public void setHotelsBean(RoomResult hotel1Bean) {
        this.hotelsBean = hotel1Bean;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        setRoomContentText(hotelsBean.getHotelBean().getName());
        mAdapter.setDatas(hotelsBean.getHotelBean().getRooms());


        mPhone.setText(hotelsBean.getHotelBean().getContact_phone());
        mLocation.setText(hotelsBean.getHotelBean().getAddress());
        setImageList(hotelsBean.getHotelBean().getImagesList());

    }

    @Override
    public HotelBean getHotelsBean() {
        return hotelsBean.getHotelBean();
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
        mAdapter.setPrice_type(searchItem.adultCount);
    }
}
