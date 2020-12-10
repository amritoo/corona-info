package app.coronainfo.coronainfo.model;

import androidx.annotation.NonNull;

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

    @NonNull
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
