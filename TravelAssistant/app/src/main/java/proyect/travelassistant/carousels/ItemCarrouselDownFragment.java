package proyect.travelassistant.carousels;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import proyect.travelassistant.R;

/**
 * Created by Pablo on 03/08/2017.
 */

public class ItemCarrouselDownFragment extends Fragment {

    private ImageView iv_item;
    private TextView tv_item;

    public static Fragment newInstance(Context context, String urlImage, String text) {
        Bundle b = new Bundle();
        b.putString("urlImage", urlImage);
        b.putString("text", text);

        return Fragment.instantiate(context, ItemCarrouselDownFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_item_carrousel_down, container, false);

        String urlImage = (String) this.getArguments().getSerializable("urlImage");
        String text = (String) this.getArguments().getSerializable("text");

        iv_item = (ImageView) rootView.findViewById(R.id.imageViewItemDown);
        Ion.with(iv_item).error(R.drawable.pixel_vacio).load(urlImage);

        tv_item = (TextView) rootView.findViewById(R.id.textViewItemDown);
        tv_item.setText(text);

        return rootView;
    }
}
