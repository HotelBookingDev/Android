package sf.hotel.com.hotel_client.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.FloatingSearchView.OnFocusChangeListener;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelSearchView;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISearchView;
import sf.hotel.com.hotel_client.view.presenter.person.SearchPresenter;

public class SearchFragment extends BaseFragment implements ISearchView {

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.floating_search_view)
    HotelSearchView mSearchView;

    SearchPresenter mSearchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        initView();
        mSearchPresenter = new SearchPresenter(this);
        return view;
    }

    private void initView() {
        mSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            if (!oldQuery.equals("") && newQuery.equals("")) {
                mSearchView.clearSuggestions();
            } else {
                mSearchPresenter.getmLocalOrder();
            }
        });
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                mSearchPresenter.query(currentQuery);
            }
        });
        mSearchView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocus() {
                mSearchView.clearQuery();
            }

            @Override
            public void onFocusCleared() {
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void showProgress() {
        mSearchView.showProgress();
    }

    @Override
    public void dismissProgress() {
        mSearchView.hideProgress();
    }

    @Override
    public void showSearch(List<LocalOrder> localOrders) {
        if (localOrders != null && localOrders.size() > 0) {
            mSearchView.swapSuggestions(localOrders);
        }
    }
}
