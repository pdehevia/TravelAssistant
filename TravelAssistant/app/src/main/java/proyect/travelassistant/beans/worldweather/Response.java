package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;

import proyect.travelassistant.beans.worldweather.DataBean;

/**
 * Created by Pablo on 05/11/2016.
 */

public class Response implements Serializable {
    private DataBean data;

    public Response() {
    }

    public Response(DataBean data) {
        this.data = data;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
