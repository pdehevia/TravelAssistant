package proyect.travelassistant.beans;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.beans.openweather.OpenWeatherResponseBean;
import proyect.travelassistant.sqlite.CustomRecomsForConsult;

/**
 * Created by Pablo on 02/08/2017.
 */

public class AuxiliarData {

    private static AuxiliarData singletonInstance;

    private List<OpenWeatherResponseBean> itemsCarrousel = new ArrayList<>();
    private ScheduledInfoBean scheduledInfo = new ScheduledInfoBean();
    private int itemId;
    private long consultId;
    private boolean updateCustomRecoms;
    private CustomRecomsForConsult customRecom;

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

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public boolean isUpdateCustomRecoms() {
        return updateCustomRecoms;
    }

    public void setUpdateCustomRecoms(boolean updateCustomRecoms) {
        this.updateCustomRecoms = updateCustomRecoms;
    }

    public CustomRecomsForConsult getCustomRecom() {
        return customRecom;
    }

    public void setCustomRecom(CustomRecomsForConsult customRecom) {
        this.customRecom = customRecom;
    }
}
