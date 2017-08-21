package proyect.travelassistant.beans.openweather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 31/07/2017.
 */

public class OpenWeatherResponseBean implements Serializable {
    private CoordBean coord;
    private List<OpenWeatherBean> weather;
    private String base;
    private MainWeatherBean main;
    private String visibility;
    private WindBean wind;
    private String dt;
    private SysBean sys;
    private CloudsBean clouds;
    private String id;
    private String name;
    private String cod;

    public OpenWeatherResponseBean() {
    }

    public OpenWeatherResponseBean(CoordBean coord, List<OpenWeatherBean> weather, String base, MainWeatherBean main, String visibility, WindBean wind, String dt, SysBean sys, CloudsBean clouds, String id, String name, String cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.dt = dt;
        this.sys = sys;
        this.clouds = clouds;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public CoordBean getCoord() {
        return coord;
    }

    public void setCoord(CoordBean coord) {
        this.coord = coord;
    }

    public List<OpenWeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(List<OpenWeatherBean> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainWeatherBean getMain() {
        return main;
    }

    public void setMain(MainWeatherBean main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public SysBean getSys() {
        return sys;
    }

    public void setSys(SysBean sys) {
        this.sys = sys;
    }

    public CloudsBean getClouds() {
        return clouds;
    }

    public void setClouds(CloudsBean clouds) {
        this.clouds = clouds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
