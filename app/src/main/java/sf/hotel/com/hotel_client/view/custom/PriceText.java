package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class PriceText extends LinearLayout{


    TextView pointName,
            priceName,
            pointContent,
            priceContent, addText;


    LinearLayout.LayoutParams pointNameParams,
            priceNameParams,
            pointContentParams,
            priceContentParams,
            addParams;




    String poins;
    String price;

    int contentColor;

    int addColor;

    int nameColor;

    public PriceText(Context context) {
        this(context, null);
    }


    public PriceText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PriceText);

        poins = typedArray.getString(R.styleable.PriceText_price_text_poins);
        price = typedArray.getString(R.styleable.PriceText_price_text_price);
        nameColor = typedArray.getResourceId(R.styleable.PriceText_price_text_name_color, R.color.hotels_text_color_green);
        addColor = typedArray.getResourceId(R.styleable.PriceText_price_text_add_color, R.color.hotels_text_color_gray);
        contentColor = typedArray.getResourceId(R.styleable.PriceText_price_text_content_color, R.color.hotels_text_color_gray);


        typedArray.recycle();

        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);

        addPointsContent();
        addPoitnsName();
        addAddText();
        addPriceContent();
        addPriceName();
    }

    private void addPriceName() {
        priceName = new TextView(getContext());
        priceNameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        priceNameParams.setMargins(5,0,0,0);
        priceName.setText("CNY");
        priceName.setTextColor(getContext().getResources().getColor(nameColor));
        addView(priceName, priceNameParams);
    }

    private void addPriceContent() {
        priceContent = new TextView(getContext());
        priceContentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        priceContentParams.setMargins(8,0,0,0);
        priceContent.setTextColor(getContext().getResources().getColor(contentColor));
        priceContent.setText("1000");
        addView(priceContent);
    }

    private void addAddText() {
        addText = new TextView(getContext());
        addParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addParams.setMargins(8,0,0,0);
        addText.setText("+");
        addText.setTextColor(getContext().getResources().getColor(addColor));
        addView(addText, addParams);
    }

    private void addPoitnsName() {

        pointName = new TextView(getContext());
        pointNameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pointNameParams.setMargins(5,0,0,0);
        pointName.setText("points");
        pointName.setTextColor(getContext().getResources().getColor(nameColor));
        addView(pointName, pointNameParams);
    }

    private void addPointsContent() {
        pointContent = new TextView(getContext());
        pointContentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pointContent.setTextColor(getContext().getResources().getColor(contentColor));
        pointContent.setText("1000");
        addView(pointContent);
    }


  
    public void setPoinsAndPrice(String poins, String price){
        this.poins = poins;
        this.price = price;

        pointContent.setText(poins);
        priceContent.setText(price);
    }
}
