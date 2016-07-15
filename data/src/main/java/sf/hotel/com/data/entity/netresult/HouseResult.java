package sf.hotel.com.data.entity.netresult;

import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.HouseBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class HouseResult {

    /**
     * name : 商务大床
     * house_imgs : []
     * housePackages : [{"front_price":350,"need_point":20,"detail":"细节","id":1},{"front_price":340,"need_point":0,"detail":"","id":2},{"front_price":340,"need_point":0,"detail":"","id":3},{"front_price":340,"need_point":0,"detail":"","id":4},{"front_price":340,"need_point":0,"detail":"","id":5},{"front_price":340,"need_point":0,"detail":"","id":6},{"front_price":340,"need_point":0,"detail":"","id":7},{"front_price":340,"need_point":0,"detail":"","id":8},{"front_price":340,"need_point":0,"detail":"","id":9},{"front_price":340,"need_point":0,"detail":"","id":10},{"front_price":340,"need_point":0,"detail":"","id":11},{"front_price":340,"need_point":0,"detail":"","id":12},{"front_price":340,"need_point":0,"detail":"","id":13},{"front_price":340,"need_point":0,"detail":"","id":14},{"front_price":340,"need_point":0,"detail":"","id":15}]
     * hotel : 1
     * id : 1
     */

    private List<HouseBean> hotel_houses;


    public List<HouseBean> getHotel_houses() {
        return hotel_houses;
    }

    public void setHotel_houses(List<HouseBean> hotel_houses) {
        this.hotel_houses = hotel_houses;
    }
}
