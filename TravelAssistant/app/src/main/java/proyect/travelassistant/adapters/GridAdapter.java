package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.worldweather.MonthBean;

/**
 * Created by Pablo on 09/11/2016.
 */

public class GridAdapter extends BaseAdapter {
    List<MonthBean> months;
    Context context;

    public GridAdapter(Context context, List<MonthBean> months) {
        this.context = context;
        this.months = months;
    }

    @Override
    public int getCount() {
        return months.size();
    }

    @Override
    public Object getItem(int position) {
        return months.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        gridView = inflater.inflate(R.layout.grid_custom_item, null);
        TextView text_title = (TextView) gridView.findViewById(R.id.textViewMonth);
        TextView text_valueMin = (TextView) gridView.findViewById(R.id.textViewTempMin_2);
        TextView text_valueMax = (TextView) gridView.findViewById(R.id.textViewTempMax_2);

        String index = months.get(position).getIndex();
        switch (index){
            case "1":
                text_title.setText(context.getString(R.string.month_1));
                break;
            case "2":
                text_title.setText(context.getString(R.string.month_2));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "3":
                text_title.setText(context.getString(R.string.month_3));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "4":
                text_title.setText(context.getString(R.string.month_4));
                break;
            case "5":
                text_title.setText(context.getString(R.string.month_5));
                break;
            case "6":
                text_title.setText(context.getString(R.string.month_6));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "7":
                text_title.setText(context.getString(R.string.month_7));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "8":
                text_title.setText(context.getString(R.string.month_8));
                break;
            case "9":
                text_title.setText(context.getString(R.string.month_9));
                break;
            case "10":
                text_title.setText(context.getString(R.string.month_10));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "11":
                text_title.setText(context.getString(R.string.month_11));
                gridView.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_border_relleno));
                break;
            case "12":
                text_title.setText(context.getString(R.string.month_12));
                break;
        }
        text_valueMin.setText(""+months.get(position).getAvgMinTemp()+" ºC");
        text_valueMax.setText(""+months.get(position).getAbsMaxTemp()+" ºC");

        return gridView;
    }
}