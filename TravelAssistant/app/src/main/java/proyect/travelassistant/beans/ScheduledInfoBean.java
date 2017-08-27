package proyect.travelassistant.beans;

import java.util.List;

import proyect.travelassistant.sqlite.NotifForConsult;

/**
 * Created by Pablo on 26/08/2017.
 */

public class ScheduledInfoBean {
    private List<String> recoms;
    private String nameCity;
    private NotifForConsult nfc;

    public ScheduledInfoBean() {
    }

    public ScheduledInfoBean(List<String> recoms, String nameCity, NotifForConsult nfc) {
        this.recoms = recoms;
        this.nameCity = nameCity;
        this.nfc = nfc;
    }

    public List<String> getRecoms() {
        return recoms;
    }

    public void setRecoms(List<String> recoms) {
        this.recoms = recoms;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public NotifForConsult getNfc() {
        return nfc;
    }

    public void setNfc(NotifForConsult nfc) {
        this.nfc = nfc;
    }
}
