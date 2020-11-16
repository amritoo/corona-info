package app.coronainfo.coronainfo;

public interface Constants {

    // Preferences
    String PREFERENCE_FILE_NAME = "corona_info_app";
    String CURRENT_THEME = "theme";
    String HOME_COUNTRY_CODE = "home_country_code";

    /* Times */
    long SPLASH_OUT_TIME = 3000;

    // News intent
    String NEWS_URL = "news_url";
    String NEWS_IMAGE_URL = "news_image";
    String NEWS_TITLE = "news_title";
    String NEWS_DATE = "news_date";
    String NEWS_DESCRIPTION = "news_description";

    // Api URLs
    String TRAVEL_ALERT_URL = "http://api.coronatracker.com/v1/travel-alert";
    String NEWS_TRENDING_URL = "http://api.coronatracker.com/news/trending";
    String GLOBAL_STATES_URL = "http://api.coronatracker.com/v3/stats/worldometer/global";
    String COUNTRY_STATES_URL = "http://api.coronatracker.com/v3/stats/worldometer/country";
}
