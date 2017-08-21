package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.worldweather.WeatherBean;

/**
 * Created by Pablo on 10/11/2016.
 */

public class RecyclerAdapterWeather extends RecyclerView.Adapter<RecyclerAdapterWeather.ViewHolder> {
    private LayoutInflater inflador;
    private List<WeatherBean> lista;

    public RecyclerAdapterWeather(Context context, List<WeatherBean> lista) {
        this.lista = lista;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.weatherlist_custom_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        String fecha =  lista.get(i).getDate();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date aux = input.parse(fecha);
            fecha = output.format(aux);

        } catch (ParseException e) {}

        holder.textViewTituloDia.setText(fecha);
        holder.textViewValorMaxDia.setText(lista.get(i).getMaxtempC()+" ºC");
        holder.textViewValorMinDia.setText(lista.get(i).getMintempC()+" ºC");
        holder.textViewValorAmanDia.setText(lista.get(i).getAstronomy().get(0).getSunrise());
        holder.textViewValorAtarDia.setText(lista.get(i).getAstronomy().get(0).getSunset());

        int j = 0;

        holder.textViewTituloHora1.setText("00:00");
        Ion.with(holder.imageViewHora1).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora1.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora1.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad1.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora2.setText("03:00");
        Ion.with(holder.imageViewHora2).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora2.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora2.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad2.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora3.setText("06:00");
        Ion.with(holder.imageViewHora3).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora3.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora3.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad3.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora4.setText("09:00");
        Ion.with(holder.imageViewHora4).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora4.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora4.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad4.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora5.setText("12:00");
        Ion.with(holder.imageViewHora5).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora5.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora5.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad5.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora6.setText("15:00");
        Ion.with(holder.imageViewHora6).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora6.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora6.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad6.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora7.setText("18:00");
        Ion.with(holder.imageViewHora7).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora7.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora7.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad7.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;

        holder.textViewTituloHora8.setText("21:00");
        Ion.with(holder.imageViewHora8).error(R.drawable.pixel_vacio).load(lista.get(i).getHourly().get(j).getWeatherIconUrl().get(0).getValue());
        holder.textViewGradosHora8.setText(""+lista.get(i).getHourly().get(j).getTempC()+" ºC");
        holder.textViewVientoHora8.setText(""+lista.get(i).getHourly().get(j).getWindspeedKmph()+" Km/h");
        holder.textViewHumedad8.setText(""+lista.get(i).getHourly().get(j).getHumidity()+" %");
        j++;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTituloDia, textViewValorMaxDia, textViewValorMinDia, textViewValorAmanDia, textViewValorAtarDia;

        private TextView textViewTituloHora1, textViewGradosHora1, textViewVientoHora1, textViewHumedad1;
        private ImageView imageViewHora1;
        private TextView textViewTituloHora2, textViewGradosHora2, textViewVientoHora2, textViewHumedad2;
        private ImageView imageViewHora2;
        private TextView textViewTituloHora3, textViewGradosHora3, textViewVientoHora3, textViewHumedad3;
        private ImageView imageViewHora3;
        private TextView textViewTituloHora4, textViewGradosHora4, textViewVientoHora4, textViewHumedad4;
        private ImageView imageViewHora4;
        private TextView textViewTituloHora5, textViewGradosHora5, textViewVientoHora5, textViewHumedad5;
        private ImageView imageViewHora5;
        private TextView textViewTituloHora6, textViewGradosHora6, textViewVientoHora6, textViewHumedad6;
        private ImageView imageViewHora6;
        private TextView textViewTituloHora7, textViewGradosHora7, textViewVientoHora7, textViewHumedad7;
        private ImageView imageViewHora7;
        private TextView textViewTituloHora8, textViewGradosHora8, textViewVientoHora8, textViewHumedad8;
        private ImageView imageViewHora8;

        ViewHolder(View itemView) {
            super(itemView);
            textViewTituloDia = (TextView)itemView.findViewById(R.id.textViewTitleDay);
            textViewValorMaxDia = (TextView)itemView.findViewById(R.id.textViewValueMaxDay);
            textViewValorMinDia = (TextView)itemView.findViewById(R.id.textViewValueMinDay);
            textViewValorAmanDia = (TextView)itemView.findViewById(R.id.textViewValueAmanDay);
            textViewValorAtarDia = (TextView)itemView.findViewById(R.id.textViewValueAtarDay);

            textViewTituloHora1 = (TextView)itemView.findViewById(R.id.textViewTitleHour1);
            imageViewHora1 = (ImageView)itemView.findViewById(R.id.imageViewHour1);
            textViewGradosHora1 = (TextView)itemView.findViewById(R.id.textViewDegreesHour1);
            textViewVientoHora1 = (TextView)itemView.findViewById(R.id.textViewWindHour1);
            textViewHumedad1 = (TextView)itemView.findViewById(R.id.textViewHumidity1);

            textViewTituloHora2 = (TextView)itemView.findViewById(R.id.textViewTitleHour2);
            imageViewHora2 = (ImageView)itemView.findViewById(R.id.imageViewHour2);
            textViewGradosHora2 = (TextView)itemView.findViewById(R.id.textViewDegreesHour2);
            textViewVientoHora2 = (TextView)itemView.findViewById(R.id.textViewWindHour2);
            textViewHumedad2 = (TextView)itemView.findViewById(R.id.textViewHumidity2);

            textViewTituloHora3 = (TextView)itemView.findViewById(R.id.textViewTitleHour3);
            imageViewHora3 = (ImageView)itemView.findViewById(R.id.imageViewHour3);
            textViewGradosHora3 = (TextView)itemView.findViewById(R.id.textViewDegreesHour3);
            textViewVientoHora3 = (TextView)itemView.findViewById(R.id.textViewWindHour3);
            textViewHumedad3 = (TextView)itemView.findViewById(R.id.textViewHumidity3);

            textViewTituloHora4 = (TextView)itemView.findViewById(R.id.textViewTitleHour4);
            imageViewHora4 = (ImageView)itemView.findViewById(R.id.imageViewHour4);
            textViewGradosHora4 = (TextView)itemView.findViewById(R.id.textViewDegreesHour4);
            textViewVientoHora4 = (TextView)itemView.findViewById(R.id.textViewWindHour4);
            textViewHumedad4 = (TextView)itemView.findViewById(R.id.textViewHumidity4);

            textViewTituloHora5 = (TextView)itemView.findViewById(R.id.textViewTitleHour5);
            imageViewHora5 = (ImageView)itemView.findViewById(R.id.imageViewHour5);
            textViewGradosHora5 = (TextView)itemView.findViewById(R.id.textViewDegreesHora5);
            textViewVientoHora5 = (TextView)itemView.findViewById(R.id.textViewWindHour5);
            textViewHumedad5 = (TextView)itemView.findViewById(R.id.textViewHumidity5);

            textViewTituloHora6 = (TextView)itemView.findViewById(R.id.textViewTituloHour6);
            imageViewHora6 = (ImageView)itemView.findViewById(R.id.imageViewHour6);
            textViewGradosHora6 = (TextView)itemView.findViewById(R.id.textViewDegreesHour6);
            textViewVientoHora6 = (TextView)itemView.findViewById(R.id.textViewWindHour6);
            textViewHumedad6 = (TextView)itemView.findViewById(R.id.textViewHumidity6);

            textViewTituloHora7 = (TextView)itemView.findViewById(R.id.textViewTitleHour7);
            imageViewHora7 = (ImageView)itemView.findViewById(R.id.imageViewHour7);
            textViewGradosHora7 = (TextView)itemView.findViewById(R.id.textViewDegreesHour7);
            textViewVientoHora7 = (TextView)itemView.findViewById(R.id.textViewWindHour7);
            textViewHumedad7 = (TextView)itemView.findViewById(R.id.textViewHumidity7);

            textViewTituloHora8 = (TextView)itemView.findViewById(R.id.textViewTitleHour8);
            imageViewHora8 = (ImageView)itemView.findViewById(R.id.imageViewHour8);
            textViewGradosHora8 = (TextView)itemView.findViewById(R.id.textViewDegreesHour8);
            textViewVientoHora8 = (TextView)itemView.findViewById(R.id.textViewWindHour8);
            textViewHumedad8 = (TextView)itemView.findViewById(R.id.textViewHumidity8);
        }
    }

}