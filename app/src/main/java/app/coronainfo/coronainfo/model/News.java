package app.coronainfo.coronainfo.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class News {

    private String url, urlToImage, title, date, description;

    public News() {
    }

    public News(String url, String urlToImage, String title, String date, String description) {
        this.url = url;
        this.urlToImage = urlToImage;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getDateFormatted() {
        String res = "";
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Date d = input.parse(date);
            res = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(d);
        } catch (ParseException e) {
            Log.e("News", "getDateFormatted:dateParseError", e);
        }
        return res;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
