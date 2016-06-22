package sf.hotel.com.data.entity.netresult;

import java.util.List;

import sf.hotel.com.data.entity.HotelBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class HotelResult {

    private List<HotelBean> hotel;

    public List<HotelBean> getHotel() {
        return hotel;
    }

    public void setHotel(List<HotelBean> hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "HotelResult{" +
                "hotel=" + hotel +
                '}';
    }
}
