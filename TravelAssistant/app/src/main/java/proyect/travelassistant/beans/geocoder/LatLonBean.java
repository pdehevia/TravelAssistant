package proyect.travelassistant.beans.geocoder;

/**
 * Created by Pablo on 08/09/2017.
 */

public class LatLonBean {
    private double lat;
    private double lng;

    public LatLonBean() {
    }

    public LatLonBean(double lat, double lon) {
        this.lat = lat;
        this.lng = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
