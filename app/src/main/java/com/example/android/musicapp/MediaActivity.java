package com.example.android.musicapp;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MediaActivity extends AppCompatActivity implements Runnable,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    // UI Components
    private SeekBar mSeekBar;
    private ImageButton mStartMedia;
    private ImageButton mStopMedia;
    private MediaPlayer mMediaPlayer;
    private TextView mTextSong;
    private TextView mTextSinger;
    private ImageView mImageArtwork;

    // Various identifiers
    private Typeface mCustomFont;
    private String mIntentMessage;
    private String mSongCategory;
    private String mSong;
    private String mSinger;
    private String mSongResource;
    private String mArtworkResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [] intentMsgArray = new String[3];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        Bundle bundle = getIntent().getExtras();
        mIntentMessage = bundle.getString("message");

        // Splits intent message received into song, singer, and song category
        intentMsgArray = mIntentMessage.split("\\|");
        mSong = intentMsgArray[0];
        mSinger = intentMsgArray[1];
        mSongCategory = intentMsgArray[2];

        // Initialize UI Components
        mTextSong = (TextView) findViewById(R.id.text_song);
        mTextSinger = (TextView) findViewById(R.id.text_singer);
        mImageArtwork = (ImageView) findViewById(R.id.image_artwork);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar_media);
        mStartMedia = (ImageButton) findViewById(R.id.imagebutton_play_pause);
        mStopMedia = (ImageButton) findViewById(R.id.imagebutton_stop);

        // Set OnClickListeners on clickable items
        mStartMedia.setOnClickListener(this);
        mStopMedia.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setEnabled(false);

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getAssets(), "fonts/opensans_semibold.ttf");
        mTextSong.setTypeface(mCustomFont);
        mTextSinger.setTypeface(mCustomFont);

        // Get song resource name
        getSongResource();

        // Get artwork reesource name
        getArtworkResource();

        // Display Media Details and Image
        displayMediaDetails();
    }

    /**
     * This method gets song resource name of the song selected
     */
    public void getSongResource() {
        mSongResource = mSongCategory + "__";
        mSongResource += mSong.replace("'", "").toLowerCase() + "_";
        mSongResource += mSinger.replace(",", "").toLowerCase();
        mSongResource = mSongResource.replaceAll(" ", "_");
    }

    /**
     * This method gets artwork drawable name of the song selected
     */
    public void getArtworkResource() {
        if (mSongCategory.equals(getString(R.string.category_sleep))) {
            mArtworkResource = getString(R.string.text_artwork);
        } else {
            mArtworkResource = "artwork__";
            mArtworkResource += mSong.replace("'", "").toLowerCase();
            mArtworkResource = mArtworkResource.replaceAll(" ", "_");
        }
    }

    /**
     * This method displays Media details - song title, singer name, artwork
     */
    public void displayMediaDetails() {
        mTextSong.setText(mSong);
        mTextSinger.setText(mSinger);

        int resId = getResources().getIdentifier(mArtworkResource, "drawable", getPackageName());
        mImageArtwork.setImageResource(resId);
    }

    /**
     * This method identifies progress while media is playing and sets position on SeekBar
     */
    public void run() {
        int currentPosition = mMediaPlayer.getCurrentPosition();
        int total = mMediaPlayer.getDuration();

        while (mMediaPlayer != null && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mMediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            mSeekBar.setProgress(currentPosition);
        }
    }

    /**
     * This method handles playing, pausing or stopping media file
     */
    public void onClick(View v) {
        if (v.equals(mStartMedia)) {
            if (mMediaPlayer == null) {
                int resId = getResources().getIdentifier(mSongResource, "raw", getPackageName());
                mMediaPlayer = MediaPlayer.create(MediaActivity.this, resId);
                mSeekBar.setEnabled(true);
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mStartMedia.setBackground(ContextCompat.getDrawable(MediaActivity.this, R.drawable.media_play));
            } else {
                mMediaPlayer.start();
                mStartMedia.setBackground(ContextCompat.getDrawable(MediaActivity.this, R.drawable.media_pause));
                mSeekBar.setMax(mMediaPlayer.getDuration());
                new Thread(this).start();
            }
        }

        if (v.equals(mStopMedia) && mMediaPlayer != null) {
           if (mMediaPlayer.isPlaying() || mMediaPlayer.getDuration() > 0) {
                mMediaPlayer.stop();
                mMediaPlayer = null;
                mSeekBar.setProgress(0);
                MediaActivity.this.finish();
           }
        }

    }

    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        try {
            if (mMediaPlayer.isPlaying() || mMediaPlayer != null) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                }
            } else if (mMediaPlayer == null) {
                Toast.makeText(getApplicationContext(), "Media is not running",
                        Toast.LENGTH_SHORT).show();
                seekBar.setProgress(0);
            }
        } catch (Exception e) {
            Log.e("MediaActivity", "SeekBar not responding" + e);
            seekBar.setEnabled(false);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
