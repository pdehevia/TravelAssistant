package proyect.travelassistant.beans.geocoder;

import java.util.List;

/**
 * Created by Pablo on 08/09/2017.
 */

public class Result {
    private List<AddressComponents> address_components;
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
    private List<String> types;

    public Result() {
    }

    public Result(List<AddressComponents> address_components, String formatted_address, Geometry geometry, String place_id, List<String> types) {
        this.address_components = address_components;
        this.formatted_address = formatted_address;
        this.geometry = geometry;
        this.place_id = place_id;
        this.types = types;
    }

    public List<AddressComponents> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<AddressComponents> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
