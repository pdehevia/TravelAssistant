package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 05/11/2016.
 */

public class HourlyBean implements Serializable {
    private String time;
    private String tempC;
    private String tempF;
    private String windspeedMiles;
    private String windspeedKmph;
    private String winddirDegree;
    private String winddir16Point;
    private String weatherCode;
    private List<WeatherIconUrlBean> weatherIconUrl;
    private List<WeatherDescBean> weatherDesc;
    private List<LangEsBean> lang_es;
    private String precipMM;
    private String humidity;
    private String visibility;
    private String pressure;
    private String cloudcover;
    private String HeatIndexC;
    private String HeatIndexF;
    private String DewPointC;
    private String DewPointF;
    private String WindChillC;
    private String WindChillF;
    private String WindGustMiles;
    private String WindGustKmph;
    private String FeelsLikeC;
    private String FeelsLikeF;
    private String chanceofrain;
    private String chanceofremdry;
    private String chanceofwindy;
    private String chanceofovercast;
    private String chanceofsunshine;
    private String chanceoffrost;
    private String chanceofhightemp;
    private String chanceoffog;
    private String chanceofsnow;
    private String chanceofthunder;

    public HourlyBean() {
    }

    public HourlyBean(String time, String tempC, String tempF, String windspeedMiles, String windspeedKmph, String winddirDegree, String winddir16Point, String weatherCode, List<WeatherIconUrlBean> weatherIconUrl, List<WeatherDescBean> weatherDesc, List<LangEsBean> lang_es, String precipMM, String humidity, String visibility, String pressure, String cloudcover, String heatIndexC, String heatIndexF, String dewPointC, String dewPointF, String windChillC, String windChillF, String windGustMiles, String windGustKmph, String feelsLikeC, String feelsLikeF, String chanceofrain, String chanceofremdry, String chanceofwindy, String chanceofovercast, String chanceofsunshine, String chanceoffrost, String chanceofhightemp, String chanceoffog, String chanceofsnow, String chanceofthunder) {
        this.time = time;
        this.tempC = tempC;
        this.tempF = tempF;
        this.windspeedMiles = windspeedMiles;
        this.windspeedKmph = windspeedKmph;
        this.winddirDegree = winddirDegree;
        this.winddir16Point = winddir16Point;
        this.weatherCode = weatherCode;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
        this.lang_es = lang_es;
        this.precipMM = precipMM;
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        HeatIndexC = heatIndexC;
        HeatIndexF = heatIndexF;
        DewPointC = dewPointC;
        DewPointF = dewPointF;
        WindChillC = windChillC;
        WindChillF = windChillF;
        WindGustMiles = windGustMiles;
        WindGustKmph = windGustKmph;
        FeelsLikeC = feelsLikeC;
        FeelsLikeF = feelsLikeF;
        this.chanceofrain = chanceofrain;
        this.chanceofremdry = chanceofremdry;
        this.chanceofwindy = chanceofwindy;
        this.chanceofovercast = chanceofovercast;
        this.chanceofsunshine = chanceofsunshine;
        this.chanceoffrost = chanceoffrost;
        this.chanceofhightemp = chanceofhightemp;
        this.chanceoffog = chanceoffog;
        this.chanceofsnow = chanceofsnow;
        this.chanceofthunder = chanceofthunder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
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

    public String getHeatIndexC() {
        return HeatIndexC;
    }

    public void setHeatIndexC(String heatIndexC) {
        HeatIndexC = heatIndexC;
    }

    public String getHeatIndexF() {
        return HeatIndexF;
    }

    public void setHeatIndexF(String heatIndexF) {
        HeatIndexF = heatIndexF;
    }

    public String getDewPointC() {
        return DewPointC;
    }

    public void setDewPointC(String dewPointC) {
        DewPointC = dewPointC;
    }

    public String getDewPointF() {
        return DewPointF;
    }

    public void setDewPointF(String dewPointF) {
        DewPointF = dewPointF;
    }

    public String getWindChillC() {
        return WindChillC;
    }

    public void setWindChillC(String windChillC) {
        WindChillC = windChillC;
    }

    public String getWindChillF() {
        return WindChillF;
    }

    public void setWindChillF(String windChillF) {
        WindChillF = windChillF;
    }

    public String getWindGustMiles() {
        return WindGustMiles;
    }

    public void setWindGustMiles(String windGustMiles) {
        WindGustMiles = windGustMiles;
    }

    public String getWindGustKmph() {
        return WindGustKmph;
    }

    public void setWindGustKmph(String windGustKmph) {
        WindGustKmph = windGustKmph;
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

    public String getChanceofrain() {
        return chanceofrain;
    }

    public void setChanceofrain(String chanceofrain) {
        this.chanceofrain = chanceofrain;
    }

    public String getChanceofremdry() {
        return chanceofremdry;
    }

    public void setChanceofremdry(String chanceofremdry) {
        this.chanceofremdry = chanceofremdry;
    }

    public String getChanceofwindy() {
        return chanceofwindy;
    }

    public void setChanceofwindy(String chanceofwindy) {
        this.chanceofwindy = chanceofwindy;
    }

    public String getChanceofovercast() {
        return chanceofovercast;
    }

    public void setChanceofovercast(String chanceofovercast) {
        this.chanceofovercast = chanceofovercast;
    }

    public String getChanceofsunshine() {
        return chanceofsunshine;
    }

    public void setChanceofsunshine(String chanceofsunshine) {
        this.chanceofsunshine = chanceofsunshine;
    }

    public String getChanceoffrost() {
        return chanceoffrost;
    }

    public void setChanceoffrost(String chanceoffrost) {
        this.chanceoffrost = chanceoffrost;
    }

    public String getChanceofhightemp() {
        return chanceofhightemp;
    }

    public void setChanceofhightemp(String chanceofhightemp) {
        this.chanceofhightemp = chanceofhightemp;
    }

    public String getChanceoffog() {
        return chanceoffog;
    }

    public void setChanceoffog(String chanceoffog) {
        this.chanceoffog = chanceoffog;
    }

    public String getChanceofsnow() {
        return chanceofsnow;
    }

    public void setChanceofsnow(String chanceofsnow) {
        this.chanceofsnow = chanceofsnow;
    }

    public String getChanceofthunder() {
        return chanceofthunder;
    }

    public void setChanceofthunder(String chanceofthunder) {
        this.chanceofthunder = chanceofthunder;
    }
}
