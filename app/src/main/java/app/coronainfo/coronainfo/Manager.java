package app.coronainfo.coronainfo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {

    private final String TAG = "Manager";
    private RequestQueue requestQueue;
    private ArrayList<String> countries;

    public Manager(Context context) {
        requestQueue = Volley.newRequestQueue(context);

        // TODO: set country data
        this.countries = new ArrayList<>(Arrays.asList("Default", "Suresh Dasari", "Trishika Dasari",
                "1", "2", "3", "4", "5", "6", "1", "2", "3", "4", "5", "6", "7"));
        String locale = context.getResources().getConfiguration().locale.getCountry();
    }

    public States getWorldStates() {
        final States[] states = new States[1];
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
                    states[0] = new States(totalConfirmed, totalDeaths, totalRecovered, totalNewCases, totalNewDeaths, totalActiveCases, created);
                } catch (JSONException e) {
                    Log.e(TAG, "getWorldStates:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getWorldStates:requestError", error);
            }
        });
        requestQueue.add(request);

        return states[0];
    }

    public States getCountryStates(String countryCode) {
        final States[] states = new States[1];
        String countryUrl = Constants.COUNTRY_STATES_URL.concat("?countryCode=").concat(countryCode);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, countryUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response.toString(2));
                    int totalConfirmed = response.getInt("totalConfirmed");
                    System.out.println(totalConfirmed);
                    int totalDeaths = response.getInt("totalDeaths");
                    int totalRecovered = response.getInt("totalRecovered");
                    int dailyConfirmed = response.getInt("dailyConfirmed");
                    int dailyDeaths = response.getInt("dailyDeaths");
                    int activeCases = response.getInt("activeCases");
                    String lastUpdated = response.getString("lastUpdated");
                    states[0] = new States(totalConfirmed, totalDeaths, totalRecovered, dailyConfirmed, dailyDeaths, activeCases, lastUpdated);
                } catch (JSONException e) {
                    Log.e(TAG, "getCountryStates:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getCountryStates:requestError", error);
            }
        });
        requestQueue.add(request);

//        return states[0];
        return new States(1, 2, 3, 4, 5, 6, "2020-03-21T13:00:13.000Z");
    }

    public List<News> getNews(String country) {
        // TODO get country code
        return getNews(country, "BD", 33, 0);
    }

    public List<News> getNews(String country, String countryCode, int limit, int offset) {
        final List<News> newsList = new ArrayList<>();
        String newsUrl = Constants.NEWS_TRENDING_URL.concat("?")
                .concat("limit=").concat(String.valueOf(limit))
                .concat("offset=").concat(String.valueOf(offset))
                .concat("country=").concat(country)
                .concat("countryCode=").concat(countryCode);

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
                        newsList.add(new News(url, urlToImage, title, publishedAt, content));
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "getNews:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getNews:requestError", error);
            }
        });
        requestQueue.add(request);

        return newsList;
    }

    public List<String> getAllCountryList() {
        return countries;
    }

    public List<?> getCountryCodes() {
        String travelUrl = Constants.TRAVEL_ALERT_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, travelUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // todo
                    String countryCode = response.getString("countryCode");
                    String countryName = response.getString("countryName");
                    String alertMessage = response.getString("alertMessage");
                    String time = response.getString("publishedDate");
                } catch (JSONException e) {
                    Log.e(TAG, "countryStates:jsonError", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "countryStatesError", error);
            }
        });
        requestQueue.add(request);

        return null;
    }

}
