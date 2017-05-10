package com.example.android.musicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create ArrayList of playlist categories
        ArrayList<PlayList> playLists = new ArrayList<PlayList>();
        playLists.add(new PlayList("Romance is in the air", R.drawable.icon_romance));
        playLists.add(new PlayList("Cheer me up", R.drawable.icon_cheer));
        playLists.add(new PlayList("Time to exercise", R.drawable.icon_exercise));
        playLists.add(new PlayList("Let's celebrate", R.drawable.icon_celebration));
        playLists.add(new PlayList("Help me sleep", R.drawable.icon_sleep));

        // Create PlayListAdapter object to display listview
        PlayListAdapter adapter = new PlayListAdapter(this, playLists);
        ListView listView = (ListView) findViewById(R.id.list_playlist);
        listView.setBackgroundResource(R.drawable.music_app_splash);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;

        TextView textView = (TextView) view.findViewById(R.id.text_playlist_category);
        String playlistText = textView.getText().toString();

        Intent intent = new Intent(context, PlaylistActivity.class);
        intent.putExtra("message", playlistText);
        startActivity(intent);
    }
}
