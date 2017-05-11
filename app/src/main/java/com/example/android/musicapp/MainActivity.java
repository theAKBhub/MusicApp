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

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface customFont;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set custom typeface
        customFont = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/opensans_regular.ttf");

        // Create ArrayList of playlist categories
        ArrayList<PlayList> playLists = new ArrayList<PlayList>();
        playLists.add(new PlayList(getString(R.string.label_romantic), R.drawable.icon_romance));
        playLists.add(new PlayList(getString(R.string.label_cheer), R.drawable.icon_cheer));
        playLists.add(new PlayList(getString(R.string.label_dance), R.drawable.icon_exercise));
        playLists.add(new PlayList(getString(R.string.label_celebration), R.drawable.icon_celebration));
        playLists.add(new PlayList(getString(R.string.label_sleep), R.drawable.icon_sleep));

        ListView listView = (ListView) findViewById(R.id.list_playlist);
        listView.setBackgroundResource(R.drawable.music_app_splash);

        // Inflate header view
        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.list_header, listView, false);
        TextView textHeader = (TextView) headerView.findViewById(R.id.text_listview_header);
        textHeader.setTypeface(customFont);
        textHeader.setText(getString(R.string.text_main_activity));

        // Add header view to the ListView
        listView.addHeaderView(headerView);

        // Create PlayListAdapter object to display listview
        PlayListAdapter adapter = new PlayListAdapter(this, playLists);
        listView.setAdapter(adapter);

        // Set OnClickListener on ListView to identify the item on ListView clicked by user
        // Text on the ListView item clicked is passed on to PlaylistActivity
        listView.setOnItemClickListener(this);
    }

    /**
     * This method identifies ListView item clicked and launches PlaylistActivity
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;

        TextView textView = (TextView) view.findViewById(R.id.text_playlist_category);
        String playlistText = textView.getText().toString();

        Intent intent = new Intent(context, PlaylistActivity.class);
        intent.putExtra("message", playlistText);
        startActivity(intent);
    }
}
