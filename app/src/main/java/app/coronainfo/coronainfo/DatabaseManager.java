package app.coronainfo.coronainfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    public DatabaseManager(@Nullable Context context) {
        super(context, "coronainfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE states (??)";

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

    public boolean add() {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("key", "value");

        long insert = database.insert("coronainfo.db", null, cv);
        return insert != -1;
    }

    public List<States> view() {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM states";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int as = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return null;
    }

}
