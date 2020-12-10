package app.coronainfo.coronainfo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.coronainfo.coronainfo.model.Constants;
import app.coronainfo.coronainfo.model.News;
import app.coronainfo.coronainfo.model.States;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_states = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_STATES + " (\n"
                + Constants.TABLE_COLUMN_CODE + " TEXT PRIMARY KEY NOT NULL,\n"
                + Constants.TABLE_COLUMN_COUNTRY + " TEXT NOT NULL,\n"
                + Constants.TABLE_COLUMN_DATE + " TEXT,\n"
                + Constants.TABLE_COLUMN_TOTAL_CONFIRMED + " INT,\n"
                + Constants.TABLE_COLUMN_TOTAL_DEATH + " INT,\n"
                + Constants.TABLE_COLUMN_TOTAL_RECOVERED + " INT,\n"
                + Constants.TABLE_COLUMN_NEW_CONFIRMED + " INT,\n"
                + Constants.TABLE_COLUMN_NEW_DEATH + " INT,\n"
                + Constants.TABLE_COLUMN_ACTIVE + " INT,\n"
                + Constants.TABLE_COLUMN_ALERT + " TEXT\n"
                + ");";
        db.execSQL(table_states);

        String table_news = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_NEWS + " (\n"
                + Constants.TABLE_COLUMN_URL + " TEXT PRIMARY KEY NOT NULL,\n"
                + Constants.TABLE_COLUMN_DATE + " TEXT,\n"
                + Constants.TABLE_COLUMN_TITLE + " TEXT NOT NULL,\n"
                + Constants.TABLE_COLUMN_DESCRIPTION + " TEXT,\n"
                + Constants.TABLE_COLUMN_IMAGE_URL + " TEXT,\n"
                + Constants.TABLE_COLUMN_CODE + " TEXT NOT NULL\n"
                + ");";
        db.execSQL(table_news);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_STATES);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NEWS);

        // create new tables
        onCreate(db);
    }

    public boolean addStates(String countryCode, String country, States states) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.TABLE_COLUMN_COUNTRY, country);
        cv.put(Constants.TABLE_COLUMN_DATE, states.getTime());
        cv.put(Constants.TABLE_COLUMN_NEW_CONFIRMED, states.getNewConfirmed());
        cv.put(Constants.TABLE_COLUMN_NEW_DEATH, states.getNewDeath());
        cv.put(Constants.TABLE_COLUMN_TOTAL_RECOVERED, states.getTotalRecovered());
        cv.put(Constants.TABLE_COLUMN_TOTAL_CONFIRMED, states.getTotalConfirmed());
        cv.put(Constants.TABLE_COLUMN_TOTAL_DEATH, states.getTotalDeath());
        cv.put(Constants.TABLE_COLUMN_ACTIVE, states.getActiveCase());

        // update if exists, else insert
        int update = db.update(Constants.TABLE_STATES, cv, Constants.TABLE_COLUMN_CODE + " = ?", new String[]{countryCode});
        if (update == 0) {
            cv.put(Constants.TABLE_COLUMN_CODE, countryCode);
            long insert = db.insert(Constants.TABLE_STATES, null, cv);
            db.close();
            return insert != -1;
        }
        db.close();
        return true;
    }

    public boolean addAlertToStates(String countryCode, String alert) {
        alert = alert.replace("|", "");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.TABLE_COLUMN_ALERT, alert);

        int update = db.update(Constants.TABLE_STATES, cv, Constants.TABLE_COLUMN_CODE + " = ?", new String[]{countryCode});
        db.close();
        return update != 0;
    }

    public boolean addNews(String countryCode, News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.TABLE_COLUMN_DATE, news.getDate());
        cv.put(Constants.TABLE_COLUMN_TITLE, news.getTitle());
        cv.put(Constants.TABLE_COLUMN_DESCRIPTION, news.getDescription());
        cv.put(Constants.TABLE_COLUMN_IMAGE_URL, news.getUrlToImage());
        cv.put(Constants.TABLE_COLUMN_CODE, countryCode);

        // update if exists, else insert
        int update = db.update(Constants.TABLE_NEWS, cv, Constants.TABLE_COLUMN_URL + " = ?", new String[]{news.getUrl()});
        if (update == 0) {
            cv.put(Constants.TABLE_COLUMN_URL, news.getUrl());
            long insert = db.insert(Constants.TABLE_NEWS, null, cv);
            db.close();
            return insert != -1;
        }
        db.close();
        return true;
    }

    public States getCountryStates(String countryCode) {
        States states = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constants.TABLE_STATES + " WHERE " + Constants.TABLE_COLUMN_CODE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{countryCode});

        if (cursor.moveToFirst()) {
            states = new States();
            states.setCountry(cursor.getString(1));
            states.setTime(cursor.getString(2));
            states.setTotalConfirmed(cursor.getInt(3));
            states.setTotalDeath(cursor.getInt(4));
            states.setTotalRecovered(cursor.getInt(5));
            states.setNewConfirmed(cursor.getInt(6));
            states.setNewDeath(cursor.getInt(7));
            states.setActiveCase(cursor.getInt(8));
            states.setAlertMessage(cursor.getString(9));
        }

        cursor.close();
        db.close();
        return states;
    }

    public List<States> getAllCountryStates() {
        List<States> statesList = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constants.TABLE_STATES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            statesList = new ArrayList<>();
            do {
                States states = new States();
                states.setCountry(cursor.getString(1));
                states.setTime(cursor.getString(2));
                states.setTotalConfirmed(cursor.getInt(3));
                states.setTotalDeath(cursor.getInt(4));
                states.setTotalRecovered(cursor.getInt(5));
                states.setNewConfirmed(cursor.getInt(6));
                states.setNewDeath(cursor.getInt(7));
                states.setActiveCase(cursor.getInt(8));
                states.setAlertMessage(cursor.getString(9));
                statesList.add(states);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return statesList;
    }

    public States getGlobalStates() {
        States states = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constants.TABLE_STATES + " WHERE " + Constants.TABLE_COLUMN_CODE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{Constants.GLOBAL});

        if (cursor.moveToFirst()) {
            states = new States();
            states.setCountry(cursor.getString(1));
            states.setTime(cursor.getString(2));
            states.setTotalConfirmed(cursor.getInt(3));
            states.setTotalDeath(cursor.getInt(4));
            states.setTotalRecovered(cursor.getInt(5));
            states.setNewConfirmed(cursor.getInt(6));
            states.setNewDeath(cursor.getInt(7));
            states.setActiveCase(cursor.getInt(8));
            states.setAlertMessage(cursor.getString(9));
        }

        cursor.close();
        db.close();
        return states;
    }

    public List<News> getNews(int limit, String contains) {
        List<News> newsList = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query;
        // if contains == null, then select any news
        if (contains == null) {
            query = "SELECT * FROM " + Constants.TABLE_NEWS
                    + " ORDER BY " + Constants.TABLE_COLUMN_DATE + " DESC "
                    + " LIMIT " + limit;
        } else {
            query = "SELECT * FROM " + Constants.TABLE_NEWS
                    + " WHERE " + Constants.TABLE_COLUMN_TITLE + " LIKE " + " %" + contains + "% "
                    + " OR " + Constants.TABLE_COLUMN_DESCRIPTION + " LIKE " + " %" + contains + "% "
                    + " ORDER BY " + Constants.TABLE_COLUMN_DATE + " DESC "
                    + " LIMIT " + limit;
        }
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            newsList = new ArrayList<>();
            do {
                News news = new News();
                news.setUrl(cursor.getString(0));
                news.setDate(cursor.getString(1));
                news.setTitle(cursor.getString(2));
                news.setDescription(cursor.getString(3));
                news.setUrlToImage(cursor.getString(4));
                newsList.add(news);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return newsList;
    }


    public Map<String, String> getCountryCode() {
        Map<String, String> codeMap = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + Constants.TABLE_COLUMN_CODE + ", " + Constants.TABLE_COLUMN_COUNTRY + " FROM " + Constants.TABLE_STATES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            codeMap = new HashMap<>();
            do {
                String code = cursor.getString(0);
                String country = cursor.getString(1);
                codeMap.put(country, code);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return codeMap;
    }

}
