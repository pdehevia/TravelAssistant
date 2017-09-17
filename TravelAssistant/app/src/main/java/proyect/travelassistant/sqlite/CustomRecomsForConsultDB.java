package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgarcia on 14/9/17.
 */

public class CustomRecomsForConsultDB {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyIdConsult(),
            DatabaseHelper.getKeyDescripcion(), DatabaseHelper.getKeyDone()};

    public CustomRecomsForConsultDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long createCustomRecomForConsult(long idConsulta, String description, boolean done) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        initialValues.put(DatabaseHelper.getKeyDescripcion(), description);
        if(done){
            initialValues.put(DatabaseHelper.getKeyDone(),1);
        }else{
            initialValues.put(DatabaseHelper.getKeyDone(),0);
        }
        return database.insert(DatabaseHelper.getDatabaseTableCustomrecomsForQuerys(), null, initialValues);
    }

    public boolean deleteCustomRecomForConsult(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableCustomrecomsForQuerys(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateCustomRecomForConsulta(long rowId, long idConsulta, String description, boolean done) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyIdConsult(), idConsulta);
        args.put(DatabaseHelper.getKeyDescripcion(), description);
        if(done){
            args.put(DatabaseHelper.getKeyDone(),1);
        }else{
            args.put(DatabaseHelper.getKeyDone(),0);
        }
        return database.update(DatabaseHelper.getDatabaseTableCustomrecomsForQuerys(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    private CustomRecomsForConsult cursorToCustomRecomForConsult(Cursor cursor) {
        CustomRecomsForConsult c = new CustomRecomsForConsult();
        c.setId(Long.parseLong(cursor.getString(0)));
        c.setConsulta(Long.parseLong(cursor.getString(1)));
        c.setDescripcion(cursor.getString(2));
        int aux = Integer.parseInt(cursor.getString(3));
        c.setDone(aux == 1);
        return c;
    }

    public List<CustomRecomsForConsult> getCustomRecomsForConsult(long idConsulta) {
        List<CustomRecomsForConsult> customRecoms = new ArrayList<>();
        String cond = "" + DatabaseHelper.getKeyIdConsult() + "=?";
        String[] args = new String[] { String.valueOf(idConsulta) };
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableCustomrecomsForQuerys(), allCols,
                cond, args, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            customRecoms.add(cursorToCustomRecomForConsult(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return customRecoms;
    }

}
