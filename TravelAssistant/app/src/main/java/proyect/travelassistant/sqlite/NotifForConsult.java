package proyect.travelassistant.sqlite;

/**
 * Created by Pablo on 26/08/2017.
 */

public class NotifForConsult {
    private long id;
    private long notificacion;
    private long consulta;
    private String fecha;
    private String hora;
    private String texto;
    private boolean activa;
    private int tipo;

    public static int DEFAULT_TYPE = 0;
    public static int RECOM_TYPE = 1;
    public static int CUSTOM_TYPE = 2;
    public static int NO_ACTIVE_TYPE = 99;

    public NotifForConsult() {
    }

    public NotifForConsult(long id, long notificacion, long consulta, String fecha, String hora, String texto, boolean activa, int tipo) {
        this.id = id;
        this.notificacion = notificacion;
        this.consulta = consulta;
        this.fecha = fecha;
        this.hora = hora;
        this.texto = texto;
        this.activa = activa;
        this.tipo = tipo;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
