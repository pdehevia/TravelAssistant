package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgarcia on 14/11/16.
 */

public class CriteryDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyDescripcion()};

    public CriteryDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createCriterio(String descripcion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyDescripcion(), descripcion);
        initialValues.put(DatabaseHelper.getKeyDone(),0);
        return database.insert(DatabaseHelper.getDatabaseTableCriterys(), null, initialValues);
    }

    public boolean deleteCriterio(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableCriterys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public List<Critery> getCriterios() {
        List<Critery> notesId = new ArrayList<Critery>();
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableCriterys(),
                allCols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            notesId.add(cursorToCriterio(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return notesId;
    }

    private Critery cursorToCriterio(Cursor cursor) {
        Critery c = new Critery();
        c.setId(Long.parseLong(cursor.getString(0)));
        c.setDescripcion(cursor.getString(1));
        return c;
    }

    public Cursor getCriterioForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] results = new String[] { DatabaseHelper.getKeyDescripcion()};
        String[] args = new String[] { String.valueOf(id) };
        return database.query(DatabaseHelper.getDatabaseTableCriterys(), results,
                cond, args, null, null, null);
    }

    public String getCriterioDescripcionForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] results = new String[] { DatabaseHelper.getKeyDescripcion()};
        String[] args = new String[] { String.valueOf(id) };
        Cursor c = database.query(DatabaseHelper.getDatabaseTableCriterys(), results,
                cond, args, null, null, null);
        c.moveToFirst();
        String desc = c.getString(0);
        c.close();
        return desc;
    }
}
