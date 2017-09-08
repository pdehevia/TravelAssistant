package proyect.travelassistant.beans.geocoder;

/**
 * Created by Pablo on 08/09/2017.
 */

public class Viewport {
    private LatLonBean northeast;
    private LatLonBean southwest;

    public Viewport() {
    }

    public Viewport(LatLonBean northeast, LatLonBean southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public LatLonBean getNortheast() {
        return northeast;
    }

    public void setNortheast(LatLonBean northeast) {
        this.northeast = northeast;
    }

    public LatLonBean getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LatLonBean southwest) {
        this.southwest = southwest;
    }
}
