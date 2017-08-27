package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Pablo on 26/08/2017.
 */

public class NotifForConsultDB {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyIdConsult(),
            DatabaseHelper.getKeyIdNotif(), DatabaseHelper.getKeyDate(), DatabaseHelper.getKeyHour(),
            DatabaseHelper.getKeyText(),DatabaseHelper.getKeyActive(), DatabaseHelper.getKeyType()};

    public NotifForConsultDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createNotificacionParaConsulta(long idConsulta, long idNotificacion, String fecha, String hora, String texto,boolean activa, int tipo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        initialValues.put(DatabaseHelper.getKeyIdNotif(), idNotificacion);
        initialValues.put(DatabaseHelper.getKeyDate(), fecha);
        initialValues.put(DatabaseHelper.getKeyHour(), hora);
        initialValues.put(DatabaseHelper.getKeyText(),texto);
        if(activa){
            initialValues.put(DatabaseHelper.getKeyActive(),1);
        }else{
            initialValues.put(DatabaseHelper.getKeyActive(),0);
        }
        initialValues.put(DatabaseHelper.getKeyType(),tipo);
        return database.insert(DatabaseHelper.getDatabaseTableNotifsForQuerys(), null, initialValues);
    }

    public boolean deleteNotificacionParaConsulta(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableNotifsForQuerys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateNotificacionParaConsulta(long rowId, long idConsulta, long idNotificacion, String fecha, String hora, String texto,boolean activa, int tipo) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        args.put(DatabaseHelper.getKeyIdNotif(), idNotificacion);
        args.put(DatabaseHelper.getKeyDate(), fecha);
        args.put(DatabaseHelper.getKeyHour(), hora);
        args.put(DatabaseHelper.getKeyText(), texto);
        if(activa){
            args.put(DatabaseHelper.getKeyActive(),1);
        }else{
            args.put(DatabaseHelper.getKeyActive(),0);
        }
        args.put(DatabaseHelper.getKeyType(), tipo);
        return database.update(DatabaseHelper.getDatabaseTableNotifsForQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    private NotifForConsult cursorToNotificacionParaConsulta(Cursor cursor) {
        NotifForConsult n = new NotifForConsult();
        n.setId(Long.parseLong(cursor.getString(0)));
        n.setConsulta(Long.parseLong(cursor.getString(1)));
        n.setNotificacion(Long.parseLong(cursor.getString(2)));
        n.setFecha(cursor.getString(3));
        n.setHora(cursor.getString(4));
        n.setTexto(cursor.getString(5));
        int aux = Integer.parseInt(cursor.getString(6));
        n.setActiva(aux == 1);
        n.setTipo(Integer.parseInt(cursor.getString(7)));
        return n;
    }

    public NotifForConsult getNotificacionParaConsultaForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        Cursor c = database.query(DatabaseHelper.getDatabaseTableNotifsForQuerys(), allCols,
                cond, args, null, null, null);
        c.moveToFirst();
        return cursorToNotificacionParaConsulta(c);
    }

    public NotifForConsult getNotificacionParaConsultaId(long idConsulta) {
        String cond = "" + DatabaseHelper.getKeyIdConsult() + "=?";
        String[] args = new String[] { String.valueOf(idConsulta) };
        Cursor c = database.query(DatabaseHelper.getDatabaseTableNotifsForQuerys(), allCols,
                cond, args, null, null, null);
        c.moveToFirst();
        return cursorToNotificacionParaConsulta(c);
    }
}