package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;

/**
 * Created by Pablo on 05/11/2016.
 */

public class AstronomyBean implements Serializable {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;

    public AstronomyBean() {
    }

    public AstronomyBean(String sunrise, String sunset, String moonrise, String moonset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }
}
