package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 05/11/2016.
 */

public class CurrentConditionBean implements Serializable {
    private String observation_time;
    private String temp_C;
    private String temp_F;
    private String weatherCode;
    private List<WeatherIconUrlBean> weatherIconUrl;
    private List<WeatherDescBean> weatherDesc;
    private List<LangEsBean> lang_es;
    private String windspeedMiles;
    private String windspeedKmph;
    private String winddirDegree;
    private String winddir16Point;
    private String precipMM;
    private String humidity;
    private String visibility;
    private String pressure;
    private String cloudcover;
    private String FeelsLikeC;
    private String FeelsLikeF;

    public CurrentConditionBean() {
    }

    public CurrentConditionBean(String observation_time, String temp_C, String temp_F, String weatherCode, List<WeatherIconUrlBean> weatherIconUrl, List<WeatherDescBean> weatherDesc, List<LangEsBean> lang_es, String windspeedMiles, String windspeedKmph, String winddirDegree, String winddir16Point, String precipMM, String humidity, String visibility, String pressure, String cloudcover, String feelsLikeC, String feelsLikeF) {
        this.observation_time = observation_time;
        this.temp_C = temp_C;
        this.temp_F = temp_F;
        this.weatherCode = weatherCode;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
        this.lang_es = lang_es;
        this.windspeedMiles = windspeedMiles;
        this.windspeedKmph = windspeedKmph;
        this.winddirDegree = winddirDegree;
        this.winddir16Point = winddir16Point;
        this.precipMM = precipMM;
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        FeelsLikeC = feelsLikeC;
        FeelsLikeF = feelsLikeF;
    }

    public String getObservation_time() {
        return observation_time;
    }

    public void setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public String getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(String temp_C) {
        this.temp_C = temp_C;
    }

    public String getTemp_F() {
        return temp_F;
    }

    public void setTemp_F(String temp_F) {
        this.temp_F = temp_F;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<WeatherIconUrlBean> getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(List<WeatherIconUrlBean> weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public List<WeatherDescBean> getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(List<WeatherDescBean> weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public List<LangEsBean> getLang_es() {
        return lang_es;
    }

    public void setLang_es(List<LangEsBean> lang_es) {
        this.lang_es = lang_es;
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public void setWindspeedMiles(String windspeedMiles) {
        this.windspeedMiles = windspeedMiles;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(String windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getWinddirDegree() {
        return winddirDegree;
    }

    public void setWinddirDegree(String winddirDegree) {
        this.winddirDegree = winddirDegree;
    }

    public String getWinddir16Point() {
        return winddir16Point;
    }

    public void setWinddir16Point(String winddir16Point) {
        this.winddir16Point = winddir16Point;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(String precipMM) {
        this.precipMM = precipMM;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(String cloudcover) {
        this.cloudcover = cloudcover;
    }

    public String getFeelsLikeC() {
        return FeelsLikeC;
    }

    public void setFeelsLikeC(String feelsLikeC) {
        FeelsLikeC = feelsLikeC;
    }

    public String getFeelsLikeF() {
        return FeelsLikeF;
    }

    public void setFeelsLikeF(String feelsLikeF) {
        FeelsLikeF = feelsLikeF;
    }
}
