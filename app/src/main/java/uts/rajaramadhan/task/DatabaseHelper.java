package uts.rajaramadhan.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda_db_new"; // Nama database baru
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_AGENDA = "agenda";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA_AGENDA = "nama_agenda"; // Pastikan nama kolom ini benar
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_STATUS = "status";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_AGENDA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA_AGENDA + " TEXT NOT NULL, " +
                    COLUMN_DESKRIPSI + " TEXT, " +
                    COLUMN_STATUS + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db);
    }

    public boolean addAgenda(String nama, String deskripsi, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA_AGENDA, nama);
        contentValues.put(COLUMN_DESKRIPSI, deskripsi);
        contentValues.put(COLUMN_STATUS, status);
        long result = db.insert(TABLE_AGENDA, null, contentValues);
        return result != -1; // Mengembalikan true jika berhasil
    }

    public Cursor getAllAgenda() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_AGENDA, null);
    }
}
