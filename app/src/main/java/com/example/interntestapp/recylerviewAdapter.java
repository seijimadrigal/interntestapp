package com.example.interntestapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class recylerviewAdapter extends RecyclerView.Adapter<recylerviewAdapter.ViewHolder> {


    private static final String TAG = "RECYCLER VIEW ADAPTER";

    private ArrayList<String> itemImageeMedium;
    private ArrayList<String> itemTitle;
    private ArrayList<String> itemImageOriginal;
    private ArrayList<String> itemDescription;
    private ArrayList<String> itemUrl;

    private ArrayList<String> seasons;
    private ArrayList<String> airdates;
    private ArrayList<String> airstamps;
    private ArrayList<String> airtimes;
    private ArrayList<String> runtimes;

    private Context context;


    public recylerviewAdapter(  ArrayList<String> itemUrl,
                                ArrayList<String> seasons,
                                ArrayList<String> airdates,
                                ArrayList<String> airstamps,
                                ArrayList<String> airtimes,
                                ArrayList<String> runtimes,
                                ArrayList<String> itemImageOriginal,
                                ArrayList<String> itemImageMedium,
                                ArrayList<String> itemTitle,
                                ArrayList<String> itemDescription, Context context) {
        this.itemUrl = itemUrl;
        this.seasons = seasons;
        this.airdates = airdates;
        this.airstamps = airstamps;
        this.airtimes = airtimes;
        this.runtimes = runtimes;
        this.itemTitle = itemTitle;
        this.itemImageeMedium = itemImageMedium;
        this.itemImageOriginal =itemImageOriginal;
        this.itemDescription = itemDescription;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_items_subgroup, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Log.d(TAG, "onBindViewHolder: Method Called");
        Log.d(TAG, "onBindViewHolder: "+ itemTitle.get(i));
        viewHolder.showTitle.setText(itemTitle.get(i));
        viewHolder.showDescription.setText(itemDescription.get(i));
        Glide.with(context).load(itemImageeMedium.get(i)).into(viewHolder.showImage);
       // Picasso.with(context).load(itemImageeMedium.get(i)).resize(400,200).into(viewHolder.showImage);



        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final int position = viewHolder.getAdapterPosition();

                    Intent startEpisodeActivity = new Intent(context, episodeActivity.class);
                    startEpisodeActivity.putExtra("name", itemTitle.get(position));
                    startEpisodeActivity.putExtra("url", itemUrl.get(position));
                    startEpisodeActivity.putExtra("summary",itemDescription.get(position));
                    startEpisodeActivity.putExtra("image", itemImageOriginal.get(position));
                    startEpisodeActivity.putExtra("airtime", airtimes.get(position));
                    startEpisodeActivity.putExtra("airdate", airdates.get(position));
                    startEpisodeActivity.putExtra("airstamp", airstamps.get(position));
                    startEpisodeActivity.putExtra("runtime", runtimes.get(position));
                    context.startActivity(startEpisodeActivity);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemDescription.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView showImage;
        TextView  showTitle;
        TextView showDescription;
        RelativeLayout mainLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.showImageMedium);
            showTitle = itemView.findViewById(R.id.showTitle);
            showDescription = itemView.findViewById(R.id.showDescription);
            mainLayout =itemView.findViewById(R.id.main_layout);

        }
    }




    }



