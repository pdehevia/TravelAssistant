package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;

/**
 * Created by Pablo on 05/11/2016.
 */

public class LangEsBean implements Serializable {
    private String value;

    public LangEsBean() {
    }

    public LangEsBean(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
