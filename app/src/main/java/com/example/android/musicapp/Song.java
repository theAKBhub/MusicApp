package com.example.android.musicapp;

/**
 * Created by aditibhattacharya on 09/05/2017.
 */

public class Song {
    private String mSong;
    private String  mSinger;

    // Default constructor
    public Song (String song, String singer) {
        mSong = song;
        mSinger = singer;
    }

    /**
     * This method gets song name
     * @return song
     */
    public String getSong() {
        return mSong;
    }

    /**
     * This method gets singer name
     * @return singer
     */
    public String getSinger() {
        return mSinger;
    }

}
