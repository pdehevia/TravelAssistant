package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 03/12/2016.
 */

public class ConsultsDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(),
                                 DatabaseHelper.getKeyDate(),
                                 DatabaseHelper.getKeyDestiny(),
                                 DatabaseHelper.getKeyDays(),
                                 DatabaseHelper.getKeyDescDays(),
                                 DatabaseHelper.getKeyTMin(),
                                 DatabaseHelper.getKeyTMax(),
                                 DatabaseHelper.getKeyLat(),
                                 DatabaseHelper.getKeyLon()};

    public ConsultsDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createConsulta(String fecha, String destino, int dias, String descDias, String tMin, String tMax, double lat, double lon) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyDate(), fecha);
        initialValues.put(DatabaseHelper.getKeyDestiny(), destino);
        initialValues.put(DatabaseHelper.getKeyDays(), dias);
        initialValues.put(DatabaseHelper.getKeyDescDays(),descDias);
        initialValues.put(DatabaseHelper.getKeyTMin(),tMin);
        initialValues.put(DatabaseHelper.getKeyTMax(),tMax);
        initialValues.put(DatabaseHelper.getKeyLat(),lat);
        initialValues.put(DatabaseHelper.getKeyLon(),lon);
        return database.insert(DatabaseHelper.getDatabaseTableQuerys(), null, initialValues);
    }

    public boolean deleteConsulta(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableQuerys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateConsulta(long rowId, String fecha, String destino, int dias, String descDias, String tMin, String tMax, double lat, double lon) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyDate(), fecha);
        args.put(DatabaseHelper.getKeyDestiny(), destino);
        args.put(DatabaseHelper.getKeyDays(), dias);
        args.put(DatabaseHelper.getKeyDescDays(), descDias);
        args.put(DatabaseHelper.getKeyTMin(), tMin);
        args.put(DatabaseHelper.getKeyTMax(), tMax);
        args.put(DatabaseHelper.getKeyLat(), lat);
        args.put(DatabaseHelper.getKeyLon(), lon);
        return database.update(DatabaseHelper.getDatabaseTableQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateConsultaDate(long rowId, String fecha){
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyDate(), fecha);
        return database.update(DatabaseHelper.getDatabaseTableQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateConsulta(long rowId, int dias, String descDias, String tMin, String tMax) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyDays(), dias);
        args.put(DatabaseHelper.getKeyDescDays(), descDias);
        args.put(DatabaseHelper.getKeyTMin(), tMin);
        args.put(DatabaseHelper.getKeyTMax(), tMax);
        return database.update(DatabaseHelper.getDatabaseTableQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public List<Consult> getConsultas() {
        List<Consult> notesId = new ArrayList<Consult>();
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableQuerys(),
                allCols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            notesId.add(cursorToConsulta(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return notesId;
    }

    public Cursor getCursorConsultas() {
        return database.query(DatabaseHelper.getDatabaseTableQuerys(), allCols,
                null, null, null, null, null);
    }

    public Cursor fetchConsulta(long rowId) throws SQLException {
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableQuerys(),
                allCols, DatabaseHelper.getKeyId() + "=" + rowId, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private Consult cursorToConsulta(Cursor cursor) {
        Consult c = new Consult();
        c.setId(Long.parseLong(cursor.getString(0)));
        c.setFecha(cursor.getString(1));
        c.setDestino(cursor.getString(2));
        c.setDias(cursor.getInt(3));
        c.setDescDias(cursor.getString(4));
        c.setTemMin(cursor.getString(5));
        c.setTemMax(cursor.getString(6));
        c.setLat(Double.parseDouble(cursor.getString(7)));
        c.setLon(Double.parseDouble(cursor.getString(8)));
        return c;
    }

    public Cursor getConsultaForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        return database.query(DatabaseHelper.getDatabaseTableQuerys(), allCols,
                cond, args, null, null, null);
    }
}
