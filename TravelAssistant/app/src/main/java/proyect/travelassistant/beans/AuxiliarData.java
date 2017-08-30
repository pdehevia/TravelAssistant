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
    private ScheduledInfoBean scheduledInfo = new ScheduledInfoBean();
    private int itemId;

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

    public ScheduledInfoBean getScheduledInfo() {
        return scheduledInfo;
    }

    public void setScheduledInfo(ScheduledInfoBean scheduledInfo) {
        this.scheduledInfo = scheduledInfo;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
