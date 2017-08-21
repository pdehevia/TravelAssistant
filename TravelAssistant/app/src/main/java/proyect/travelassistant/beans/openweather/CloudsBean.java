package proyect.travelassistant.beans.openweather;

import java.io.Serializable;

/**
 * Created by Pablo on 31/07/2017.
 */

public class CloudsBean implements Serializable {
    private String all;

    public CloudsBean() {
    }

    public CloudsBean(String all) {
        this.all = all;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
