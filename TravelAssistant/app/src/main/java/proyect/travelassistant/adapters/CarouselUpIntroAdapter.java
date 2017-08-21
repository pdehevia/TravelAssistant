package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.openweather.OpenWeatherResponseBean;
import proyect.travelassistant.carousels.CarouselLayout;
import proyect.travelassistant.carousels.ItemCarouselUpFragment;


/**
 * Created by pgarcia on 29/6/17.
 */

public class CarouselUpIntroAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.7f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    public int loops;


    private CarouselUpIntroAdapter cur = null;
    private CarouselUpIntroAdapter next = null;
    private Context context;
    private List<OpenWeatherResponseBean> elements;
    private FragmentManager fragmentManager;
    private float scale;

    public CarouselUpIntroAdapter(Context context, FragmentManager fragmentManager, List<OpenWeatherResponseBean>elements) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.elements = elements;

        if(this.elements.size()<2){
            loops = 1;
        }else{
            loops = 1000;
        }

    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        int first = (elements.size() * loops) / 2;
        if (position == first)
            scale = BIG_SCALE;
        else
            scale = SMALL_SCALE;

        position = position % elements.size();
        return ItemCarouselUpFragment.newInstance(context, position, scale, this.elements.get(position));
    }

    @Override
    public int getCount() {
        return elements.size() * loops;
    }

    @Override
    public void transformPage(View page, float position) {
        CarouselLayout carouselLayout = (CarouselLayout) page.findViewById(R.id.carouselLayout);
        float scale = BIG_SCALE;
        if (position > 0) {
            scale = scale - position * DIFF_SCALE;
        } else {
            scale = scale + position * DIFF_SCALE;
        }
        if (scale < 0) scale = 0;
        carouselLayout.setScaleBoth(scale);
    }
}
