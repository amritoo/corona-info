package app.coronainfo.coronainfo.model;

public interface Constants {

    // Preferences
    String PREFERENCE_FILE_NAME = "corona_info_app";
    String CURRENT_THEME = "theme";
    String HOME_COUNTRY_CODE = "home_country_code";
    String COUNTRY_CODE = "country_code";

    // Times
    long SPLASH_OUT_TIME = 3000;

    // News intent keys
    String NEWS_URL = "news_url";
    String NEWS_IMAGE_URL = "news_image";
    String NEWS_TITLE = "news_title";
    String NEWS_DATE = "news_date";
    String NEWS_DESCRIPTION = "news_description";

    // Api URLs
    String TRAVEL_ALERT_URL = "https://api.coronatracker.com/v1/travel-alert";
    String NEWS_TRENDING_URL = "https://api.coronatracker.com/news/trending";
    String GLOBAL_STATES_URL = "https://api.coronatracker.com/v3/stats/worldometer/global";
    String COUNTRY_STATES_URL = "https://api.coronatracker.com/v3/stats/worldometer/country";

    // Database
    String DATABASE_NAME = "coronainfo.db";
    String TABLE_STATES = "states";
    String TABLE_COLUMN_CODE = "code";
    String TABLE_COLUMN_COUNTRY = "country";
    String TABLE_COLUMN_TOTAL_CONFIRMED = "totalConfirmed";
    String TABLE_COLUMN_TOTAL_DEATH = "totalDeath";
    String TABLE_COLUMN_TOTAL_RECOVERED = "totalRecovered";
    String TABLE_COLUMN_NEW_CONFIRMED = "newConfirmed";
    String TABLE_COLUMN_NEW_DEATH = "newDeath";
    String TABLE_COLUMN_ACTIVE = "activeCase";
    String TABLE_COLUMN_ALERT = "alert";
    String TABLE_COLUMN_DATE = "date";

    String TABLE_NEWS = "news";
    String TABLE_COLUMN_URL = "url";
    String TABLE_COLUMN_IMAGE_URL = "imageUrl";
    String TABLE_COLUMN_TITLE = "title";
    String TABLE_COLUMN_DESCRIPTION = "description";
    String GLOBAL = "Global";

    // json
    String NEWS_LIMIT = "100";

}
