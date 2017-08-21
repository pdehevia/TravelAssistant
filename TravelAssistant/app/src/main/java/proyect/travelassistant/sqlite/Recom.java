package proyect.travelassistant.sqlite;

import java.io.Serializable;

/**
 * Created by Pablo on 12/11/2016.
 */

public class Recom implements Serializable {
    private long id;
    private String descripcion;
    private long critero;
    private boolean visible;

    public Recom() {
    }

    public Recom(long id, String descripcion, long critero, boolean visible) {
        this.id = id;
        this.descripcion = descripcion;
        this.critero = critero;
        this.visible = visible;
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

    public long getCritero() {
        return critero;
    }

    public void setCritero(long critero) {
        this.critero = critero;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
