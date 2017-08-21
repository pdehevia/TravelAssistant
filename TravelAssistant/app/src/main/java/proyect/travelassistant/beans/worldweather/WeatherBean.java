package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;
import java.util.List;

import proyect.travelassistant.beans.worldweather.AstronomyBean;
import proyect.travelassistant.beans.worldweather.HourlyBean;

/**
 * Created by Pablo on 05/11/2016.
 */

public class WeatherBean implements Serializable {
    private String date;
    private List<AstronomyBean> astronomy;
    private String maxtempC;
    private String maxtempF;
    private String mintempC;
    private String mintempF;
    private String uvIndex;
    private List<HourlyBean> hourly;

    public WeatherBean() {
    }

    public WeatherBean(String date, List<AstronomyBean> astronomy, String maxtempC, String maxtempF, String mintempC, String mintempF, String uvIndex, List<HourlyBean> hourly) {
        this.date = date;
        this.astronomy = astronomy;
        this.maxtempC = maxtempC;
        this.maxtempF = maxtempF;
        this.mintempC = mintempC;
        this.mintempF = mintempF;
        this.uvIndex = uvIndex;
        this.hourly = hourly;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AstronomyBean> getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(List<AstronomyBean> astronomy) {
        this.astronomy = astronomy;
    }

    public String getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(String maxtempC) {
        this.maxtempC = maxtempC;
    }

    public String getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(String maxtempF) {
        this.maxtempF = maxtempF;
    }

    public String getMintempC() {
        return mintempC;
    }

    public void setMintempC(String mintempC) {
        this.mintempC = mintempC;
    }

    public String getMintempF() {
        return mintempF;
    }

    public void setMintempF(String mintempF) {
        this.mintempF = mintempF;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public List<HourlyBean> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyBean> hourly) {
        this.hourly = hourly;
    }
}
