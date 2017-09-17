package proyect.travelassistant.sqlite;

/**
 * Created by pgarcia on 14/9/17.
 */

public class CustomRecomsForConsult {
    private long id;
    private long consulta;
    private String descripcion;
    private boolean done;

    public CustomRecomsForConsult() {
    }

    public CustomRecomsForConsult(long id, long consulta, String descripcion, boolean done) {
        this.id = id;
        this.consulta = consulta;
        this.descripcion = descripcion;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConsulta() {
        return consulta;
    }

    public void setConsulta(long consulta) {
        this.consulta = consulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
