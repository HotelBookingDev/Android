package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.adapter.OnItemClickListener;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ICityView;
import sf.hotel.com.hotel_client.view.presenter.hotel.ICityPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityFragment extends BaseFragment implements ICityView {

    @BindView(R.id.fragment_city_grid)
    RecyclerView mGridRecyclerView;

    private CityListAdapter mCityListAdapter;

    private ICityPresenter mICityPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);
        mICityPresenter = new ICityPresenter(this);
        initGrid();

        initCityList();

        onRxEvent();
        return view;
    }

    private void onRxEvent() {
        RxBus.getDefault().toObservable(CityMessage.class).subscribe(new Action1<CityMessage>() {
            @Override
            public void call(CityMessage cityMessage) {
                if (cityMessage != null) {
                    switch (cityMessage.what) {
                        case CityMessage.SUCCESS:
                            ProcincesResult procincesResult = (ProcincesResult) cityMessage.obj;
                            mCityListAdapter.setList(procincesResult);
                            break;
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private void initCityList() {
        mICityPresenter.callCityList();
    }

    private void initGrid() {
        //设置3列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBottomContext(), 3);
        mGridRecyclerView.setLayoutManager(gridLayoutManager);
        mCityListAdapter = new CityListAdapter(getBottomContext());
        mCityListAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RxBus.getDefault()
                        .post(MessageFactory.createCityMessage(
                                CityMessage.ACTIVITY_FINISH_AND_RESULT,
                                mCityListAdapter.getListItem(position)));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mGridRecyclerView.setAdapter(mCityListAdapter);
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {
        showToast(msg);
    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }
}
