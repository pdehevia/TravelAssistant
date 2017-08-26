package proyect.travelassistant.sqlite;

/**
 * Created by Pablo on 26/08/2017.
 */

public class NotifForConsult {
    private long id;
    private long notificacion;
    private long consulta;
    private String fecha;
    private String text;
    private int type;

    public NotifForConsult() {
    }

    public NotifForConsult(long id, long notificacion, long consulta, String fecha, String text, int type) {
        this.id = id;
        this.notificacion = notificacion;
        this.consulta = consulta;
        this.fecha = fecha;
        this.text = text;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(long notificacion) {
        this.notificacion = notificacion;
    }

    public long getConsulta() {
        return consulta;
    }

    public void setConsulta(long consulta) {
        this.consulta = consulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
