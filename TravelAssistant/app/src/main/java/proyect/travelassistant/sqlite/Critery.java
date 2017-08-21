package proyect.travelassistant.sqlite;

/**
 * Created by pgarcia on 14/11/16.
 */

public class Critery {
    private long id;
    private String descripcion;

    public Critery() {
    }

    public Critery(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
