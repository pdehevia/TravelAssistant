package proyect.travelassistant.beans.worldweather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 05/11/2016.
 */

public class ClimateAveragesBean implements Serializable {
    private List<MonthBean> month;

    public ClimateAveragesBean() {
    }

    public ClimateAveragesBean(List<MonthBean> month) {
        this.month = month;
    }

    public List<MonthBean> getMonth() {
        return month;
    }

    public void setMonth(List<MonthBean> month) {
        this.month = month;
    }
}
