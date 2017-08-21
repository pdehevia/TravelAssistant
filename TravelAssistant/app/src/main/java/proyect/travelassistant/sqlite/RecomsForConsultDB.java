package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 03/12/2016.
 */

public class RecomsForConsultDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyIdRecom(),
                                 DatabaseHelper.getKeyIdConsult(), DatabaseHelper.getKeyDone()};

    public RecomsForConsultDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createRecomendacionParaConsulta(long idRecomendacion, long idConsulta, boolean done) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyIdRecom(), idRecomendacion);
        initialValues.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        if(done){
            initialValues.put(DatabaseHelper.getKeyDone(), 1);
        }else{
            initialValues.put(DatabaseHelper.getKeyDone(), 0);
        }
        return database.insert(DatabaseHelper.getDatabaseTableRecomesForQuerys(), null, initialValues);
    }

    public boolean deleteRecomendacionParaConsulta(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateRecomendacionParaConsulta(long rowId, long idRecomendacion, long idConsulta, boolean done) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyIdRecom(), idRecomendacion);
        args.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        if(done){
            args.put(DatabaseHelper.getKeyDone(), 1);
        }else{
            args.put(DatabaseHelper.getKeyDone(), 0);
        }

        return database.update(DatabaseHelper.getDatabaseTableRecomesForQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean deleteRecomendacionParaConsultaConIdConsulta(long rowIdConsulta) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyIdConsult() + "=" + rowIdConsulta, null) > 0;
    }

    public boolean deleteRecomendacionParaConsultaConIdRecomendacion(long rowIdRecomendacion) {
        return database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                DatabaseHelper.getKeyIdRecom()+ "=" + rowIdRecomendacion, null) > 0;
    }

    public List<RecomsForConsult> getRecomendacionParaConsulta() {
        List<RecomsForConsult> notesId = new ArrayList<RecomsForConsult>();
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                allCols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            notesId.add(cursorToRecomendacionParaConsulta(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return notesId;
    }

    public Cursor getCursorRecomendacionParaConsulta() {
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                null, null, null, null, null);
    }

    public Cursor fetchRecomendacionParaConsulta(long rowId) throws SQLException {
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(),
                allCols, DatabaseHelper.getKeyId() + "=" + rowId, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private RecomsForConsult cursorToRecomendacionParaConsulta(Cursor cursor) {
        RecomsForConsult c = new RecomsForConsult();
        c.setId(Long.parseLong(cursor.getString(0)));
        c.setRecomendacion(Long.parseLong(cursor.getString(1)));
        c.setConsulta(Long.parseLong(cursor.getString(2)));
        int done = Integer.parseInt(cursor.getString(3));
        c.setDone(done == 1);
        return c;
    }

    public Cursor getRecomendacionParaConsultaForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                cond, args, null, null, null);
    }

    public Cursor getRecomendacionesParaConsultaId(long idConsulta) {
        String cond = "" + DatabaseHelper.getKeyIdConsult() + "=?";
        String[] args = new String[] { String.valueOf(idConsulta) };
        return database.query(DatabaseHelper.getDatabaseTableRecomesForQuerys(), allCols,
                cond, args, null, null, null);
    }
}
