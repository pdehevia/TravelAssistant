package proyect.travelassistant.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import proyect.travelassistant.R;

/**
 * Created by Pablo on 12/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String TAG = "TravelAssistantDbAdapter";
    private static final String DATABASE_NAME = "data_ta.sqlite";
    private static final String DATABASE_TABLE_CRITERYS = "criterios";
    private static final String DATABASE_TABLE_RECOMS = "recomendaciones";
    private static final String DATABASE_TABLE_QUERYS = "consulta";
    private static final String DATABASE_TABLE_RECOMES_FOR_QUERYS = "recomendaciones_para_consultas";
    private static final String DATABASE_TABLE_NOTIFS_FOR_QUERYS = "notificaciones_para_consultas";

    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_DESCRIPCION = "descripcion";
    private static final String KEY_CRITERY = "criterio";
    private static final String KEY_DONE= "hecho";
    private static final String KEY_VISIBLE = "visible";
    private static final String KEY_ID_RECOM = "id_recomendacion";
    private static final String KEY_ID_CONSULT = "id_consulta";
    private static final String KEY_ID_NOTIF= "id_notificacion";
    private static final String KEY_DATE = "fecha";
    private static final String KEY_TEXT = "texto";
    private static final String KEY_TYPE = "tipo";
    private static final String KEY_HOUR = "hora";
    private static final String KEY_ACTIVE = "activa";
    private static final String KEY_DESTINY = "destino";
    private static final String KEY_DAYS = "dias";
    private static final String KEY_DESC_DAYS = "descripcion_dias";
    private static final String KEY_T_MIN = "temperatura_min";
    private static final String KEY_T_MAX = "temperatura_max";
    private static final String KEY_LAT = "latitud";
    private static final String KEY_LON = "longitud";

    /**
     * Database creation sql statement
     */
    private static final String CREATE_DATABASE_CRITERYS = "create table "
            + DATABASE_TABLE_CRITERYS + " ("
            + KEY_ID + " integer primary key, "
            + KEY_DESCRIPCION + " text not null "
            + ");";

    private static final String CREATE_DATABASE_RECOMS = "create table "
            + DATABASE_TABLE_RECOMS + " ("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_DESCRIPCION + " text not null, "
            + KEY_CRITERY + " integer, "
            + KEY_VISIBLE + " integer, "
            + "FOREIGN KEY ("+ KEY_CRITERY +") REFERENCES " + DATABASE_TABLE_CRITERYS + "("+KEY_ID+")"
            + ");";

    private static final String CREATE_DATABASE_QUERYS = "create table "
            + DATABASE_TABLE_QUERYS + " ("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_DATE + " text not null, "
            + KEY_DESTINY + " text not null, "
            + KEY_DAYS + " integer, "
            + KEY_DESC_DAYS + " text not null, "
            + KEY_T_MIN+ " text not null, "
            + KEY_T_MAX + " text not null, "
            + KEY_LAT + " real, "
            + KEY_LON + " real "
            + ");";

    private static final String CREATE_DATABASE_RECOMS_FOR_QUERYS = "create table "
            + DATABASE_TABLE_RECOMES_FOR_QUERYS + " ("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_ID_RECOM + " integer, "
            + KEY_ID_CONSULT + " integer, "
            + KEY_DONE + " integer, "
            + "FOREIGN KEY ("+ KEY_ID_RECOM +") REFERENCES " + DATABASE_TABLE_RECOMS + "("+KEY_ID+") , "
            + "FOREIGN KEY ("+ KEY_ID_CONSULT +") REFERENCES " + DATABASE_TABLE_QUERYS + "("+KEY_ID+")"
            + ");";

    private static final String CREATE_DATABASE_NOTIFS_FOR_QUERYS = "create table "
            + DATABASE_TABLE_NOTIFS_FOR_QUERYS + " ("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_ID_CONSULT + " integer, "
            + KEY_ID_NOTIF + " integer, "
            + KEY_DATE + " text not null, "
            + KEY_HOUR + " text not null, "
            + KEY_TEXT + " text not null, "
            + KEY_ACTIVE + " integer, "
            + KEY_TYPE + " integer, "
            + "FOREIGN KEY ("+ KEY_ID_CONSULT +") REFERENCES " + DATABASE_TABLE_QUERYS + "("+KEY_ID+")"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("PRAGMA foreign_keys=ON");

        db.execSQL(CREATE_DATABASE_CRITERYS);
        //Inicializar BBDD CRITERIOS
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (0, '"+this.context.getResources().getString(R.string.rec_bbdd_00)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (1, '"+this.context.getResources().getString(R.string.rec_bbdd_01)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (2, '"+this.context.getResources().getString(R.string.rec_bbdd_02)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (3, '"+this.context.getResources().getString(R.string.rec_bbdd_03)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (4, '"+this.context.getResources().getString(R.string.rec_bbdd_04)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (5, '"+this.context.getResources().getString(R.string.rec_bbdd_05)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (6, '"+this.context.getResources().getString(R.string.rec_bbdd_06)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (7, '"+this.context.getResources().getString(R.string.rec_bbdd_07)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (8, '"+this.context.getResources().getString(R.string.rec_bbdd_08)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (9, '"+this.context.getResources().getString(R.string.rec_bbdd_09)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (10, '"+this.context.getResources().getString(R.string.rec_bbdd_10)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (11, '"+this.context.getResources().getString(R.string.rec_bbdd_11)+"')");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_CRITERYS +" VALUES (12, '"+this.context.getResources().getString(R.string.rec_bbdd_12)+"')");

        db.execSQL(CREATE_DATABASE_RECOMS);

        //Inicializar BBDD RECOMENDACIONES tipo 0 - Recomendación estándar
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r01)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r02)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r03)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r04)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r05)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r06)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r07)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r08)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r09)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r10)+"', 0, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_00_r11)+"', 0, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 1 - Recomendación por viaje de 1 a 2 días de duración

        //Inicializar BBDD RECOMENDACIONES tipo 2 - Recomendación por viaje de 3 a 7 días de duración
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_02_r01)+"', 2, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_02_r02)+"', 2, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_02_r03)+"', 2, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 3 - Recomendación por Viaje de ocio
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_03_r01)+"', 3, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_03_r02)+"', 3, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 4 - Recomendación por Viaje de trabajo
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_04_r01)+"', 4, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_04_r02)+"', 4, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 5 - Recomendación por alojamiento en Hotel/Hostal
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_05_r01)+"', 5, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 6 - Recomendación por alojamiento en casa alquilada
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_06_r01)+"', 6, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_06_r02)+"', 6, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_06_r03)+"', 6, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_06_r04)+"', 6, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 7 - Recomendación por alojamiento en casa propia
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_07_r01)+"', 7, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_07_r02)+"', 7, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 8 - Recomendación por clima lluvioso o con nieve
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_08_r01)+"', 8, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_08_r02)+"', 8, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 9 - Recomendación por temperatura fria
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_09_r01)+"', 9, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 10 -  Recomendación por temperatura calida
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_10_r01)+"', 10, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_10_r02)+"', 10, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 11 - Recomendación por transporte propio
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_11_r01)+"', 11, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_11_r02)+"', 11, 0)");

        //Inicializar BBDD RECOMENDACIONES tipo 12 - Recomendación por avión, barco, tren o autobús
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_12_r01)+"', 12, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_12_r02)+"', 12, 0)");
        db.execSQL("INSERT INTO "+ DATABASE_TABLE_RECOMS +" VALUES (null, '"+this.context.getResources().getString(R.string.rec_bbdd_12_r03)+"', 12, 0)");

        db.execSQL(CREATE_DATABASE_QUERYS);
        db.execSQL(CREATE_DATABASE_RECOMS_FOR_QUERYS);
        db.execSQL(CREATE_DATABASE_NOTIFS_FOR_QUERYS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_DATABASE_CRITERYS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_DATABASE_RECOMS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_DATABASE_QUERYS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_DATABASE_RECOMS_FOR_QUERYS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_DATABASE_NOTIFS_FOR_QUERYS);
        onCreate(db);
    }

    public static String getDatabaseTableCriterys() {
        return DATABASE_TABLE_CRITERYS;
    }

    public static String getDatabaseTableRecoms() {
        return DATABASE_TABLE_RECOMS;
    }

    public static String getDatabaseTableQuerys() {
        return DATABASE_TABLE_QUERYS;
    }

    public static String getDatabaseTableRecomesForQuerys() {
        return DATABASE_TABLE_RECOMES_FOR_QUERYS;
    }

    public static String getDatabaseTableNotifsForQuerys() {
        return DATABASE_TABLE_NOTIFS_FOR_QUERYS;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyDescripcion() {
        return KEY_DESCRIPCION;
    }

    public static String getKeyVisible() {
        return KEY_VISIBLE;
    }

    public static String getKeyCritery() {
        return KEY_CRITERY;
    }

    public static String getKeyIdRecom() {
        return KEY_ID_RECOM;
    }

    public static String getKeyIdConsult() {
        return KEY_ID_CONSULT;
    }

    public static String getKeyIdNotif() {
        return KEY_ID_NOTIF;
    }

    public static String getKeyDate() {
        return KEY_DATE;
    }

    public static String getKeyDestiny() {
        return KEY_DESTINY;
    }

    public static String getKeyDays() {
        return KEY_DAYS;
    }

    public static String getKeyDescDays() {
        return KEY_DESC_DAYS;
    }

    public static String getKeyTMin() {
        return KEY_T_MIN;
    }

    public static String getKeyTMax() {
        return KEY_T_MAX;
    }

    public static String getKeyDone() {
        return KEY_DONE;
    }

    public static String getKeyLat() {
        return KEY_LAT;
    }

    public static String getKeyLon() {
        return KEY_LON;
    }

    public static String getKeyText() {
        return KEY_TEXT;
    }

    public static String getKeyType() {
        return KEY_TYPE;
    }

    public static String getKeyHour() {
        return KEY_HOUR;
    }

    public static String getKeyActive() {
        return KEY_ACTIVE;
    }
}
