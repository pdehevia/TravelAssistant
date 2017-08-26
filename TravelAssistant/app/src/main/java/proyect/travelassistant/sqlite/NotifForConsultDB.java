package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 26/08/2017.
 */

public class NotifForConsultDB {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyIdConsult(),
            DatabaseHelper.getKeyIdNotif(), DatabaseHelper.getKeyDate()};

    public NotifForConsultDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createNotificacionParaConsulta(long idConsulta, long idNotificacion, String fecha) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        initialValues.put(DatabaseHelper.getKeyIdNotif(), idNotificacion);
        initialValues.put(DatabaseHelper.getKeyDate(), fecha);
        return database.insert(DatabaseHelper.getDatabaseTableRecomesForQuerys(), null, initialValues);
    }

    public boolean deleteNotificacionParaConsulta(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateNotificacionParaConsulta(long rowId, long idConsulta, long idNotificacion, String fecha) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        args.put(DatabaseHelper.getKeyIdNotif(), idNotificacion);
        args.put(DatabaseHelper.getKeyDate(), fecha);
        return database.update(DatabaseHelper.getDatabaseTableRecomesForQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean deleteRecomendacionParaConsultaConIdConsulta(long rowIdConsulta) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyIdConsult() + "=" + rowIdConsulta, null) > 0;
    }

    public boolean deleteRecomendacionParaConsultaConIdNotificacion(long idNotificacion) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyIdNotif()+ "=" + idNotificacion, null) > 0;
    }

    public List<NotifForConsult> getNotificacionParaConsulta() {
        List<NotifForConsult> notesId = new ArrayList<NotifForConsult>();
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                allCols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            notesId.add(cursorToNotificacionParaConsulta(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return notesId;
    }

    public Cursor getCursorNotificacionParaConsulta() {
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                null, null, null, null, null);
    }

    public Cursor fetchNotificacionParaConsulta(long rowId) throws SQLException {
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                allCols, DatabaseHelper.getKeyId() + "=" + rowId, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private NotifForConsult cursorToNotificacionParaConsulta(Cursor cursor) {
        NotifForConsult n = new NotifForConsult();
        n.setId(Long.parseLong(cursor.getString(0)));
        n.setConsulta(Long.parseLong(cursor.getString(1)));
        n.setNotificacion(Long.parseLong(cursor.getString(2)));
        n.setFecha(cursor.getString(3));
        return n;
    }

    public Cursor getNotificacionParaConsultaForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                cond, args, null, null, null);
    }

    public Cursor getNotificacionParaConsultaId(long idConsulta) {
        String cond = "" + DatabaseHelper.getKeyIdConsult() + "=?";
        String[] args = new String[] { String.valueOf(idConsulta) };
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                cond, args, null, null, null);
    }
}