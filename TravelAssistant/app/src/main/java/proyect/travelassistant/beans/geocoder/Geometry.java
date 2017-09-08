package proyect.travelassistant.beans.geocoder;

/**
 * Created by Pablo on 08/09/2017.
 */

public class Geometry {
    private Bounds bounds;
    private LatLonBean location;
    private String location_type;
    private Viewport viewport;

    public Geometry() {
    }

    public Geometry(Bounds bounds, LatLonBean location, String location_type, Viewport viewport) {
        this.bounds = bounds;
        this.location = location;
        this.location_type = location_type;
        this.viewport = viewport;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public LatLonBean getLocation() {
        return location;
    }

    public void setLocation(LatLonBean location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}
