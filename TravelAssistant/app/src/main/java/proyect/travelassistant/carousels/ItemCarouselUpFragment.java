package proyect.travelassistant.carousels;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.openweather.OpenWeatherResponseBean;

/**
 * Created by pgarcia on 29/6/17.
 */

public class ItemCarouselUpFragment extends Fragment {
    private TextView city;
    private TextView tmin;
    private TextView tmax;
    private TextView hum;
    private ImageView icon;

    public static Fragment newInstance(Context context, int pos, float scale, OpenWeatherResponseBean item) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        b.putSerializable("item",item);

        return Fragment.instantiate(context, ItemCarouselUpFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.fragment_item_carrousel_up, container, false);

        OpenWeatherResponseBean item = (OpenWeatherResponseBean) this.getArguments().getSerializable("item");
        city = (TextView) l.findViewById(R.id.textViewCityCarrousel);
        city.setText(item.getName());
        tmin = (TextView) l.findViewById(R.id.textViewTminCarrousel);
        tmin.setText(item.getMain().getTemp_min()+"ºC");
        tmax = (TextView) l.findViewById(R.id.textViewTmaxCarrousel);
        tmax.setText(item.getMain().getTemp_max()+"ºC");
        hum =  (TextView) l.findViewById(R.id.textViewHumCarrousel);
        hum.setText(item.getMain().getHumidity()+"%");

        icon = (ImageView) l.findViewById(R.id.imageViewiconCarrousel);
        String urlIcon = getResources().getString(R.string.urlIcon)+item.getWeather().get(0).getIcon()+".png";
        Ion.with(icon).error(R.drawable.pixel_vacio).load(urlIcon);

        CarouselLayout carouselLayout = (CarouselLayout) l.findViewById(R.id.carouselLayout);
        float scale = this.getArguments().getFloat("scale");
        carouselLayout.setScaleBoth(scale);

        return l;
    }
}
