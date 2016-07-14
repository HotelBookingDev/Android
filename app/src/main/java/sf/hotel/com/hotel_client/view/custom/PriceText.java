package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class PriceText extends TextView{

    String poins;
    String price;

    public PriceText(Context context) {
        this(context, null);
    }


    public PriceText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PriceText);

        poins = typedArray.getString(R.styleable.PriceText_price_text_poins);
        price = typedArray.getString(R.styleable.PriceText_price_text_price);

        typedArray.recycle();

        setPoinsAndPrice();
    }

    public void setPoinsAndPrice(){
        setPoinsAndPrice(poins, price);
    }

    public void setPoinsAndPrice(String poins, String price){
        this.poins = poins;
        this.price = price;

        String s = poins + "poins + ¥" + price + "起";
        setText(s);
    }
}
