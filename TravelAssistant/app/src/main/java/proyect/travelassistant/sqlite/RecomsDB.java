package proyect.travelassistant.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyect.travelassistant.R;


/**
 * Created by Pablo on 14/11/2016.
 */

public class RecomsDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allCols = { DatabaseHelper.getKeyId(), DatabaseHelper.getKeyDescripcion(),
                                 DatabaseHelper.getKeyCritery(), DatabaseHelper.getKeyVisible()};

    public RecomsDB(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createRecomendacion(String descripcion, long criterio, boolean visible) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.getKeyDescripcion(), descripcion);
        initialValues.put(DatabaseHelper.getKeyCritery(), criterio);
        if(visible){
            initialValues.put(DatabaseHelper.getKeyVisible(), 0);
        }else{
            initialValues.put(DatabaseHelper.getKeyVisible(), 1);
        }

        return database.insert(DatabaseHelper.getDatabaseTableRecoms(), null, initialValues);
    }

    public boolean deleteRecomendacion(long rowId) {
        return database.delete(DatabaseHelper.getDatabaseTableRecoms(),
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public boolean updateRecomendacion(long rowId, String descripcion, long criterio, boolean visible) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.getKeyDescripcion(), descripcion);
        args.put(DatabaseHelper.getKeyCritery(), criterio);
        if(visible){
            args.put(DatabaseHelper.getKeyVisible(), 0);
        }else{
            args.put(DatabaseHelper.getKeyVisible(), 1);
        }
        return database.update(DatabaseHelper.getDatabaseTableRecoms(), args,
                DatabaseHelper.getKeyId() + "=" + rowId, null) > 0;
    }

    public List<Recom> getRecomendaciones() {
        List<Recom> recomendaciones = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.getDatabaseTableRecoms(),
                allCols, null, null, null, null,DatabaseHelper.getKeyCritery()+" ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            recomendaciones.add(cursorToRecomendaciones(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return recomendaciones;
    }

    private Recom cursorToRecomendaciones(Cursor cursor) {
        Recom r = new Recom();
        r.setId(cursor.getLong(0));
        r.setDescripcion(cursor.getString(1));
        r.setCritero(cursor.getLong(2));
        if(cursor.getInt(3)==0){
            r.setVisible(true);
        }else{
            r.setVisible(false);
        }
        return r;
    }

    public Recom getRecomendacionForId(long id) {
        String cond = "" + DatabaseHelper.getKeyId() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        Cursor c = database.query(DatabaseHelper.getDatabaseTableRecoms(), allCols,
                cond, args, null, null, null);
        c.moveToFirst();
        Recom r = cursorToRecomendaciones(c);
        c.close();
        return r;
    }

    public Cursor getRecomendacionForCriterio(long id) {
        String cond = "" + DatabaseHelper.getKeyCritery() + "=?";
        String[] args = new String[] { String.valueOf(id) };
        return database.query(DatabaseHelper.getDatabaseTableRecoms(), allCols,
                cond, args, null, null, null);
    }

    public void resetDatabase(Context context) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHelper.getDatabaseTableRecoms(), null, null); //erases everything in the table.
        database.delete(DatabaseHelper.getDatabaseTableQuerys(), null, null); //erases everything in the table.
        database.delete(DatabaseHelper.getDatabaseTableRecomesForQuerys(), null, null); //erases everything in the table.

        //Inicializar BBDD RECOMENDACIONES tipo 0 - Recomendación estándar
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r01)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r02)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r03)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r04)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r05)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r06)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r07)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r08)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r09)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r10)+"', 0, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_00_r11)+"', 0, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 1 - Recomendación por viaje de 1 a 2 días de duración

        //Inicializar BBDD RECOMENDACIONES tipo 2 - Recomendación por viaje de 3 a 7 días de duración
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_02_r01)+"', 2, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_02_r02)+"', 2, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_02_r03)+"', 2, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 3 - Recomendación por Viaje de ocio
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_03_r01)+"', 3, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_03_r02)+"', 3, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 4 - Recomendación por Viaje de trabajo
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_04_r01)+"', 4, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_04_r02)+"', 4, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 5 - Recomendación por alojamiento en Hotel/Hostal
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_05_r01)+"', 5, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 6 - Recomendación por alojamiento en casa alquilada
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_06_r01)+"', 6, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_06_r02)+"', 6, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_06_r03)+"', 6, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_06_r04)+"', 6, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 7 - Recomendación por alojamiento en casa propia
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_07_r01)+"', 7, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_07_r02)+"', 7, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 8 - Recomendación por clima lluvioso o con nieve
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_08_r01)+"', 8, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_08_r02)+"', 8, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 9 - Recomendación por temperatura fria
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_09_r01)+"', 9, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 10 -  Recomendación por temperatura calida
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_10_r01)+"', 10, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_10_r02)+"', 10, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 11 - Recomendación por transporte propio
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_11_r01)+"', 11, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_11_r02)+"', 11, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 12 - Recomendación por avión, barco, tren o autobús
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_12_r01)+"', 12, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_12_r02)+"', 12, 0)");
        database.execSQL("INSERT INTO "+DatabaseHelper.getDatabaseTableRecoms()+" VALUES (null, '"+context.getResources().getString(R.string.rec_bbdd_12_r03)+"', 12, 0)");
        database.close();
    }
}
