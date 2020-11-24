package app.coronainfo.coronainfo.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class States {

    private int totalConfirmed, totalDeath, totalRecovered, newConfirmed, newDeath, activeCase;
    private String time, country, alertMessage;

    public States() {
    }

    public States(int totalConfirmed, int totalDeath, int totalRecovered, int newConfirmed, int newDeath, int activeCase, String time) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeath = totalDeath;
        this.totalRecovered = totalRecovered;
        this.newConfirmed = newConfirmed;
        this.newDeath = newDeath;
        this.activeCase = activeCase;
        this.time = time;
    }

    @Override
    public String toString() {
        return "States{" +
                "totalConfirmed=" + totalConfirmed +
                ", totalDeath=" + totalDeath +
                ", totalRecovered=" + totalRecovered +
                ", newConfirmed=" + newConfirmed +
                ", newDeath=" + newDeath +
                ", activeCase=" + activeCase +
                ", time='" + time + '\'' +
                ", country='" + country + '\'' +
                ", alertMessage='" + alertMessage + '\'' +
                '}';
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

    public String getTimeFormatted() {
        // format time
        String res = "";
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Date d = input.parse(time);
            res = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault()).format(d);
        } catch (ParseException e) {
            Log.e("States", "getTimeFormatted:dateParseError", e);
        }
        return res;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

}
