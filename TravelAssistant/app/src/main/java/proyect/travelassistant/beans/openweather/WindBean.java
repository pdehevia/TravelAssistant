package proyect.travelassistant.beans.openweather;

import java.io.Serializable;

/**
 * Created by Pablo on 31/07/2017.
 */

public class WindBean implements Serializable {
    private String speed;
    private String deg;

    public WindBean() {
    }

    public WindBean(String speed, String deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }
}
