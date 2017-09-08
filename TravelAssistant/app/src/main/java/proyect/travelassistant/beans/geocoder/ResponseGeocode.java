package proyect.travelassistant.beans.geocoder;

import java.util.List;

/**
 * Created by Pablo on 08/09/2017.
 */

public class ResponseGeocode {
    private List<Result> results;
    private String status;

    public ResponseGeocode() {
    }

    public ResponseGeocode(List<Result> results, String status) {
        this.results = results;
        this.status = status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
