package sf.hotel.com.hotel_client.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sf.hotel.com.hotel_client.R;

//可能不需要这个界面，放着不做处理.
public class RegisterUserInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity())
                .inflate(R.layout.fragment_register_user, container);
        return view;
    }
}