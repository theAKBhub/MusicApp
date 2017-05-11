package com.example.android.musicapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    // Various identifiers
    private String mIntentMessage;
    private String mSongCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Bundle bundle = getIntent().getExtras();
        mIntentMessage = bundle.getString("message");

        // Display list of songs based on category chosen
        createSongList();
    }

    /**
     * Method to create list of songs to be displayed
     */
    public void createSongList() {
        Typeface customFont;

        // Set custom typeface
        customFont = Typeface.createFromAsset(PlaylistActivity.this.getAssets(), "fonts/opensans_regular.ttf");

        ArrayList<Song> songs = new ArrayList<Song>();

        if (mIntentMessage.equals(getString(R.string.label_romantic))) {
            // Set category
            mSongCategory = getString(R.string.category_romantic);
            // Add songs
            songs.add(new Song("My Heart Will Go On", "Celine Dion"));
            songs.add(new Song("I Just Called", "Stevie Wonder"));
            songs.add(new Song("Nothing's Gonna Change", "Westlife"));
            songs.add(new Song("Part Time Lover", "Stevie Wonder"));
            songs.add(new Song("Thousand Miles", "Venessa Carlton"));
            songs.add(new Song("Waiting For Tonight", "Jennifer Lopez"));

        } else if (mIntentMessage.equals(getString(R.string.label_cheer))) {
            // Set category
            mSongCategory = getString(R.string.category_cheer);
            // Add songs
            songs.add(new Song("Boogie", "Baccara"));
            songs.add(new Song("Hotel California", "Eagles"));
            songs.add(new Song("Mamma Mia", "Abba"));
            songs.add(new Song("Price Tag", "Jessie J"));
            songs.add(new Song("Time Of My Life", "Bill Medley, Jennifer Warnes"));

        } else if (mIntentMessage.equals(getString(R.string.label_celebration))) {
            // Set category
            mSongCategory = getString(R.string.category_celebration);
            // Add songs
            songs.add(new Song("Best Day Of My Life", "American Authors"));
            songs.add(new Song("Jai Ho", "Sukhwinder Singh"));
            songs.add(new Song("My Life", "Bon Jovi"));
            songs.add(new Song("One Moment In Time", "Whitney Houston"));
            songs.add(new Song("Celebration", "Madonna"));

        } else if (mIntentMessage.equals(getString(R.string.label_dance))) {
            // Set category
            mSongCategory = getString(R.string.category_dance);
            // Add songs
            songs.add(new Song("Livin' La Vida", "Ricky Martin"));
            songs.add(new Song("Thriller", "Michael Jackson"));
            songs.add(new Song("On The Floor", "Jennifer Lopez"));
            songs.add(new Song("Weather Girls", "Geri Halliwell"));
            songs.add(new Song("YMCA", "Village People"));
            songs.add(new Song("Beat It", "Michael Jackson"));

        } else if (mIntentMessage.equals(getString(R.string.label_sleep))) {
            // Set category
            mSongCategory = getString(R.string.category_sleep);
            // Add songs
            songs.add(new Song("Instrumental Track 1", "Unknown"));
            songs.add(new Song("Instrumental Track 2", "Unknown"));
            songs.add(new Song("Instrumental Track 3", "Unknown"));
            songs.add(new Song("Instrumental Track 4", "Unknown"));
            songs.add(new Song("Instrumental Track 5", "Unknown"));
        }

        ListView listView = (ListView) findViewById(R.id.list_songs);

        // Inflate header view
        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.list_header, listView, false);
        TextView textHeader = (TextView) headerView.findViewById(R.id.text_listview_header);
        textHeader.setTypeface(customFont);
        textHeader.setText(getString(R.string.text_playlist_activity));

        // Add header view to the ListView
        listView.addHeaderView(headerView);

        // Create SongAdapter object to display listview
        SongAdapter adapter = new SongAdapter(this, songs);
        listView.setAdapter(adapter);

        // Set OnClickListener on ListView to identify the item on ListView clicked by user
        // Text on the ListView item clicked is passed on to MediaActivity
        listView.setOnItemClickListener(this);
    }

    /**
     * Method to identify ListView item clicked and launch MediaActivity
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;
        String intentExtra = "";

        TextView textViewSong = (TextView) view.findViewById(R.id.text_song);
        String song = textViewSong.getText().toString();

        TextView textViewSinger = (TextView) view.findViewById(R.id.text_singer);
        String singer = textViewSinger.getText().toString();

        intentExtra = song + "|" + singer + "|" + mSongCategory;
        Intent intent = new Intent(context, MediaActivity.class);
        intent.putExtra("message", intentExtra);
        startActivity(intent);
    }

}
