package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;

/**
 * Created by Pablo on 05/11/2016.
 */

public class RequestBean implements Serializable {
    private String type;
    private String query;

    public RequestBean() {
    }

    public RequestBean(String type, String query) {
        this.type = type;
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
