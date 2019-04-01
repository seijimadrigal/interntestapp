package com.example.interntestapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class episodeActivity extends AppCompatActivity {

    private ImageView orignal_photo;
    private TextView title;
    private TextView summaryEp;
    private Context context;

    private TextView airdateView;
    private TextView airtimeView;
    private TextView airStampView;
    private TextView runtimeView;
    private TextView urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        context = getBaseContext();

        orignal_photo = findViewById(R.id.originalimage);
        title = findViewById(R.id.titleEpisode);
        summaryEp = findViewById(R.id.summaryEpisode);
        airdateView = findViewById(R.id.airdate);
        airtimeView = findViewById(R.id.airtime);
        airStampView = findViewById(R.id.airtimestamp);
        runtimeView = findViewById(R.id.runtime);
        urlView = findViewById(R.id.url);

        final String name = getIntent().getStringExtra("name");
        final String summary = getIntent().getStringExtra("summary");
        final String photoUrl = getIntent().getStringExtra("image");
        final String airdate = getIntent().getStringExtra("airdate");
        final String airtime = getIntent().getStringExtra("airtime");
        final String airstamp = getIntent().getStringExtra("airstamp");
        final String runtime = getIntent().getStringExtra("runtime");
        final String url = getIntent().getStringExtra("url");

        Glide.with(context).load(photoUrl).into(orignal_photo);

        //Picasso.with(context).load(Uri.parse(photoUrl)).resize(600,300).into(orignal_photo);
        title.setText(name);
        summaryEp.setText(summary);
        airdateView.setText(airdate);
        airtimeView.setText(airtime);
        airStampView.setText(airstamp);
        runtimeView.setText(runtime);
        urlView.setText(url);

        urlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webViewActivity = new Intent(context, webView.class);
                webViewActivity.putExtra("url", url);
                startActivity(webViewActivity);
            }
        });





    }
}
