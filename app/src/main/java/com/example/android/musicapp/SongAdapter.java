package com.example.android.musicapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditibhattacharya on 09/05/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private Typeface mCustomFont;
    private Context mContext;

    public SongAdapter (Activity context, ArrayList<Song> song) {
        super(context, 0, song);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/opensans_semibold.ttf");

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        Song currentSong = getItem(position);
        TextView textSong = (TextView) listItemView.findViewById(R.id.text_song);
        textSong.setTypeface(mCustomFont);
        textSong.setText(currentSong.getSong());

        TextView textSinger = (TextView) listItemView.findViewById(R.id.text_singer);
        textSinger.setTypeface(mCustomFont);
        textSinger.setText(currentSong.getSinger());

        return listItemView;
    }
}
