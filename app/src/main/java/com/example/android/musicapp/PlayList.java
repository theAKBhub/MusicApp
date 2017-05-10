package com.example.android.musicapp;

/**
 * Created by aditibhattacharya on 08/05/2017.
 */

public class PlayList {
    private String mPlayList;
    private int mImageResId;

    // Default constructor
    public PlayList (String playList, int imageResId) {
        mPlayList = playList;
        mImageResId = imageResId;
    }

    /**
     * This method gets playlist name
     * @return playlist
     */
    public String getPlayList() {
        return mPlayList;
    }

    /**
     * This method gets image resource id
     * @return image resource id
     */
    public int getImageResId() {
        return mImageResId;
    }
}
