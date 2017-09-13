package proyect.travelassistant.beans;

import proyect.travelassistant.sqlite.Recom;

/**
 * Created by Pablo on 22/08/2017.
 */

public class HistoricalFileBean {
    private Long idRow;
    private Recom recom;
    private boolean done;
    private boolean visible;

    public HistoricalFileBean() {
    }

    public HistoricalFileBean(Long idRow, Recom recom, boolean done, boolean visible) {
        this.idRow = idRow;
        this.recom = recom;
        this.done = done;
        this.visible = visible;
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long idRow) {
        this.idRow = idRow;
    }

    public Recom getRecom() {
        return recom;
    }

    public void setRecom(Recom recom) {
        this.recom = recom;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
