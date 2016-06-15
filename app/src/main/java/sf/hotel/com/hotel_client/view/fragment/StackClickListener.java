package sf.hotel.com.hotel_client.view.fragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/12.
 */
public interface StackClickListener {
   void showFragmentByClass(Class fragment);
   void startActivityByClass(Class clazz);
   void onFragmentBackPressed();
}
