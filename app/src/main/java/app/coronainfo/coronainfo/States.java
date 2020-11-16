package app.coronainfo.coronainfo;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class States {

    private int totalConfirmed, totalDeath, totalRecovered, newConfirmed, newDeath, activeCase;
    private String time, alertMessage;

    public States() {
    }

    public States(int totalConfirmed, int totalDeath, int totalRecovered, int newConfirmed, int newDeath, int activeCase, String time) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeath = totalDeath;
        this.totalRecovered = totalRecovered;
        this.newConfirmed = newConfirmed;
        this.newDeath = newDeath;
        this.activeCase = activeCase;
        // format time
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Date d = input.parse(time);
            this.time = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault()).format(d);
        } catch (ParseException e) {
            Log.e("States", "dateParseError", e);
        }
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getNewDeath() {
        return newDeath;
    }

    public void setNewDeath(int newDeath) {
        this.newDeath = newDeath;
    }

    public int getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(int activeCase) {
        this.activeCase = activeCase;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

}
