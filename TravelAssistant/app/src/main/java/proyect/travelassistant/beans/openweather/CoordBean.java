package proyect.travelassistant.beans.openweather;

import java.io.Serializable;

/**
 * Created by Pablo on 31/07/2017.
 */

public class CoordBean implements Serializable {
    private String lon;
    private String lat;

    public CoordBean() {
    }

    public CoordBean(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
