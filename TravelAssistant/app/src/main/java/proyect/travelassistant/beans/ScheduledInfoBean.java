package proyect.travelassistant.beans;

import java.util.List;

/**
 * Created by Pablo on 26/08/2017.
 */

public class ScheduledInfoBean {
    private Long idAlarm;
    private Long idQuery;
    private List<String> recoms;
    private String nameCity;

    public ScheduledInfoBean() {
    }

    public ScheduledInfoBean(Long idAlarm, Long idQuery, List<String> recoms, String nameCity) {
        this.idAlarm = idAlarm;
        this.idQuery = idQuery;
        this.recoms = recoms;
        this.nameCity = nameCity;
    }

    public Long getIdAlarm() {
        return idAlarm;
    }

    public void setIdAlarm(Long idAlarm) {
        this.idAlarm = idAlarm;
    }

    public Long getIdQuery() {
        return idQuery;
    }

    public void setIdQuery(Long idQuery) {
        this.idQuery = idQuery;
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
}
