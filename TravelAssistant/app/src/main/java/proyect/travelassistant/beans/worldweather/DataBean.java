package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 05/11/2016.
 */

public class DataBean implements Serializable {
    private List<RequestBean> request;
    private List<CurrentConditionBean> current_condition;
    private List<WeatherBean> weather;
    private List<ClimateAveragesBean> ClimateAverages;

    public DataBean() {
    }

    public DataBean(List<RequestBean> request, List<CurrentConditionBean> current_condition, List<WeatherBean> weather, List<ClimateAveragesBean> climateAverages) {
        this.request = request;
        this.current_condition = current_condition;
        this.weather = weather;
        ClimateAverages = climateAverages;
    }

    public List<RequestBean> getRequest() {
        return request;
    }

    public void setRequest(List<RequestBean> request) {
        this.request = request;
    }

    public List<CurrentConditionBean> getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(List<CurrentConditionBean> current_condition) {
        this.current_condition = current_condition;
    }

    public List<WeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherBean> weather) {
        this.weather = weather;
    }

    public List<ClimateAveragesBean> getClimateAverages() {
        return ClimateAverages;
    }

    public void setClimateAverages(List<ClimateAveragesBean> climateAverages) {
        ClimateAverages = climateAverages;
    }
}
