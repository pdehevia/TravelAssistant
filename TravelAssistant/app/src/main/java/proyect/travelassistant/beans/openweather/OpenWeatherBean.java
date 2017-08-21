package proyect.travelassistant.beans.openweather;

import java.io.Serializable;

/**
 * Created by Pablo on 31/07/2017.
 */

public class OpenWeatherBean implements Serializable {

    private String id;
    private String main;
    private String description;
    private String icon;


    public OpenWeatherBean() {
    }

    public OpenWeatherBean(String id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
