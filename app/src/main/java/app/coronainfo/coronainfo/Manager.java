package app.coronainfo.coronainfo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import app.coronainfo.coronainfo.dao.DatabaseManager;
import app.coronainfo.coronainfo.model.Constants;
import app.coronainfo.coronainfo.model.News;
import app.coronainfo.coronainfo.model.States;

public class Manager {

    private static final String TAG = "Manager";
    private static DatabaseManager db;

    public Manager() {
    }

    public static void initDatabase(Context context) {
        db = new DatabaseManager(context.getApplicationContext());
    }

    public static void updateWorldStates(Context context) {
        if (db == null) {
            db = new DatabaseManager(context);
        }
        String globalUrl = Constants.GLOBAL_STATES_URL;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, globalUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int totalConfirmed = response.getInt("totalConfirmed");
                    int totalDeaths = response.getInt("totalDeaths");
                    int totalRecovered = response.getInt("totalRecovered");
                    int totalNewCases = response.getInt("totalNewCases");
                    int totalNewDeaths = response.getInt("totalNewDeaths");
                    int totalActiveCases = response.getInt("totalActiveCases");
                    String created = response.getString("created");
                    States states = new States(totalConfirmed, totalDeaths, totalRecovered, totalNewCases, totalNewDeaths, totalActiveCases, created);
                    db.addStates(Constants.GLOBAL, Constants.GLOBAL, states);
                } catch (JSONException e) {
                    Log.e(TAG, "updateWorldStates:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "updateWorldStates:requestError", error);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void updateStates(Context context) {
        if (db == null) {
            db = new DatabaseManager(context);
        }
        String countryUrl = Constants.COUNTRY_STATES_URL;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, countryUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject result = response.getJSONObject(i);
                        int totalConfirmed = result.getInt("totalConfirmed");
                        int totalDeaths = result.getInt("totalDeaths");
                        int totalRecovered = result.getInt("totalRecovered");
                        int dailyConfirmed = result.getInt("dailyConfirmed");
                        int dailyDeaths = result.getInt("dailyDeaths");
                        int activeCases = result.getInt("activeCases");
                        String lastUpdated = result.getString("lastUpdated");
                        String country = result.getString("country");
                        String code = result.getString("countryCode");

                        States states = new States(totalConfirmed, totalDeaths, totalRecovered, dailyConfirmed, dailyDeaths, activeCases, lastUpdated);
                        db.addStates(code, country, states);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "updateStates:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "updateStates:requestError", error);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void updateTravelAlert(Context context) {
        if (db == null) {
            db = new DatabaseManager(context);
        }
        String alertUrl = Constants.TRAVEL_ALERT_URL;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, alertUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject result = response.getJSONObject(i);
                        String code = result.getString("countryCode");
                        String alert = result.getString("alertMessage");
                        db.addAlertToStates(code, alert);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "updateTraffic:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "updateTraffic:requestError", error);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void updateNews(Context context) {
        if (db == null) {
            db = new DatabaseManager(context);
        }
        String newsUrl = Constants.NEWS_TRENDING_URL.concat("?")
                .concat("limit=").concat(Constants.NEWS_LIMIT);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newsUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("items");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject object = result.getJSONObject(i);
                        String url = object.getString("url");
                        String urlToImage = object.getString("urlToImage");
                        String title = object.getString("title");
                        String publishedAt = object.getString("publishedAt");
                        String content = object.getString("content");
                        String code = object.getString("countryCode");

                        News news = new News(url, urlToImage, title, publishedAt, content);
                        db.addNews(code, news);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "updateNews:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "updateNews:requestError", error);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public States getGlobalStates() {
        return db.getGlobalStates();
    }

    public States getCountryStates(String countryCode) {
        return db.getCountryStates(countryCode);
    }

    public List<News> getNews(String country) {
        return db.getNews(33, country);
    }

    public Map<String, String> getCountryCodes() {
        return db.getCountryCode();
    }

}
