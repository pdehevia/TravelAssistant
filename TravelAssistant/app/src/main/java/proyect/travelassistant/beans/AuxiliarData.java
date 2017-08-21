package proyect.travelassistant.beans;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.beans.openweather.OpenWeatherResponseBean;

/**
 * Created by Pablo on 02/08/2017.
 */

public class AuxiliarData {

    private static AuxiliarData singletonInstance;

    private List<OpenWeatherResponseBean> itemsCarrousel = new ArrayList<>();

    public AuxiliarData (){

    }

    public static AuxiliarData getSingletonInstance(){
        if(null == singletonInstance){
            singletonInstance = new AuxiliarData();
        }
        return singletonInstance;
    }

    public List<OpenWeatherResponseBean> getItemsCarrousel() {
        return itemsCarrousel;
    }

    public void setItemsCarrousel(List<OpenWeatherResponseBean> itemsCarrousel) {
        this.itemsCarrousel = itemsCarrousel;
    }
}
