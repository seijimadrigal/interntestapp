package com.example.interntestapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";

    private ArrayList<String> itemTitle  = new ArrayList<>();
    private ArrayList<String> itemURL = new ArrayList<>();
    private ArrayList<String> itemImageMedium  = new ArrayList<>();
    private ArrayList<String> itemDescription  = new ArrayList<>();
    private ArrayList<String> itemImageOriginal = new ArrayList<>();

    private ArrayList<String> seasons = new ArrayList<>();
    private ArrayList<String> airdates = new ArrayList<>();
    private ArrayList<String> airstamps = new ArrayList<>();
    private ArrayList<String> airtimes = new ArrayList<>();
    private ArrayList<String> runtimes = new ArrayList<>();


    private static final String API_URL = "http://api.tvmaze.com/shows/1/episodes";
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        jsonParse();

    }

    private void startRecyclerView(){
        Log.d(TAG, "startRecyclerView: ");
        RecyclerView recyclerView = findViewById(R.id.recylerview);
        recylerviewAdapter recyclerViewAdapter = new recylerviewAdapter(
                itemURL,
                seasons,
                airdates,
                airstamps,
                airtimes,
                runtimes,
                itemImageOriginal,
                itemImageMedium,
                itemTitle,
                itemDescription,
                this);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void jsonParse() {

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        for(int i = 0 ; i < response.length(); i++){
                            try {
                                JSONObject episodeObject = response.getJSONObject(i);
                                JSONObject imageObject  = episodeObject.getJSONObject("image");
                                String medium_photo = imageObject.getString("medium");
                                String original_photo = imageObject.getString("original");

                                String title = episodeObject.getString("name");
                                String tempSummary = episodeObject.getString("summary");

                                String item_url = episodeObject.getString("url");
                                String season = episodeObject.getString("season");
                                String airdate = episodeObject.getString("airdate");
                                String airtime = episodeObject.getString("airtime");
                                String airstamp = episodeObject.getString("airstamp");
                                String runtime = episodeObject.getString("runtime");

                                String summary = tempSummary.substring(3, tempSummary.length()-4);
                                Log.d(TAG, "imageurl: " + medium_photo);
                                Log.d(TAG, "season: " + season);
                                Log.d(TAG, "season: " + season);
                                Log.d(TAG, "airdate: " + airdate);
                                Log.d(TAG, "airtime: " + airtime);
                                Log.d(TAG, "airstamp: " + airstamp);
                                Log.d(TAG, "runtime: " + runtime);

                                itemImageOriginal.add(original_photo);
                                itemDescription.add(summary);
                                itemImageMedium.add(medium_photo);
                                itemURL.add(item_url);

                                seasons.add(season);
                                airdates.add(airdate);
                                airstamps.add(airstamp);
                                airtimes.add(airtime);
                                runtimes.add(runtime);
                                itemTitle.add(title);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        startRecyclerView();

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

            mQueue.add(request);
        }






    }


