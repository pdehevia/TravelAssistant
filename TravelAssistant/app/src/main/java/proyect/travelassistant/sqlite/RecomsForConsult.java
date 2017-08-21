package proyect.travelassistant.sqlite;

/**
 * Created by Pablo on 12/11/2016.
 */

public class RecomsForConsult {
    private long id;
    private long recomendacion;
    private long consulta;
    private boolean done;

    public RecomsForConsult() {
    }

    public RecomsForConsult(long id, long recomendacion, long consulta, boolean done) {
        this.id = id;
        this.recomendacion = recomendacion;
        this.consulta = consulta;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(long recomendacion) {
        this.recomendacion = recomendacion;
    }

    public long getConsulta() {
        return consulta;
    }

    public void setConsulta(long consulta) {
        this.consulta = consulta;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
