package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import proyect.travelassistant.beans.ItemAdvice;
import proyect.travelassistant.carousels.ItemCarrouselDownFragment;

/**
 * Created by Pablo on 03/08/2017.
 */

public class CarrouselDownIntroAdapter extends FragmentStatePagerAdapter {
    private List<ItemAdvice> elements;
    private Context context;

    public CarrouselDownIntroAdapter(FragmentManager fm,Context context, List<ItemAdvice> elements) {
        super(fm);
        this.elements = elements;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemCarrouselDownFragment.newInstance(context,elements.get(position).getImage(),elements.get(position).getText());
    }

    @Override
    public int getCount() {
        return elements.size();
    }
}
