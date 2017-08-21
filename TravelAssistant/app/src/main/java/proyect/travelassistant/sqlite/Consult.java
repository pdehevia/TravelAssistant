package proyect.travelassistant.sqlite;

/**
 * Created by Pablo on 12/11/2016.
 */

public class Consult {
    private long id;
    private String fecha;
    private String destino;
    private int dias;
    private String descDias;
    private String temMin;
    private String temMax;
    private Double lat;
    private Double lon;

    public Consult() {
    }

    public Consult(long id, String fecha, String destino, int dias, String descDias, String temMin, String temMax, Double lat, Double lon) {
        this.id = id;
        this.fecha = fecha;
        this.destino = destino;
        this.dias = dias;
        this.descDias = descDias;
        this.temMin = temMin;
        this.temMax = temMax;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getDescDias() {
        return descDias;
    }

    public void setDescDias(String descDias) {
        this.descDias = descDias;
    }

    public String getTemMin() {
        return temMin;
    }

    public void setTemMin(String temMin) {
        this.temMin = temMin;
    }

    public String getTemMax() {
        return temMax;
    }

    public void setTemMax(String temMax) {
        this.temMax = temMax;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
