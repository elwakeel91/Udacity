package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link ColorsActivity} is the activity for the Colors category
 */
public class ColorsActivity extends AppCompatActivity {

    // Handles playback of all the audio files
    MediaPlayer mMediaPlayer;

    // Custom Listener to tell the media player what to do when the audio is finished
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Free up the MediaPlayer resources
            releaseMediaPlayer();
        }
    };

    // Create an Audio Manager object
    AudioManager mAudioManager;

    // Create an audio focus change listener
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {

                @Override
                public void onAudioFocusChange(int focusChange) {

                    // Check if we have lost audio focus permanently
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        // Stop the Audio and release the resources
                        releaseMediaPlayer();
                    }

                    // Else check if we have lost audio focus temporarily
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                        // Pause the audio
                        mMediaPlayer.pause();
                    }

                    // Else check if we have regained audio focus
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){

                        // Make sure the audio starts from the beginning
                        mMediaPlayer.seekTo(0);
                        // Start the audio
                        mMediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.drawable.color_red,"red", "Weṭeṭṭi", R.raw.color_red));
        words.add(new Word(R.drawable.color_mustard_yellow,"mustard yellow", "Chiwiiṭә", R.raw.color_mustard_yellow));
        words.add(new Word(R.drawable.color_dusty_yellow,"dusty yellow", "Topiisә", R.raw.color_dusty_yellow));
        words.add(new Word(R.drawable.color_green,"green", "chokokki", R.raw.color_green));
        words.add(new Word(R.drawable.color_brown,"brown", "ṭakaakki", R.raw.color_brown));
        words.add(new Word(R.drawable.color_gray,"gray", "ṭopoppi", R.raw.color_gray));
        words.add(new Word(R.drawable.color_black,"black", "kululli", R.raw.color_black));
        words.add(new Word(R.drawable.color_white,"white", "kelelli", R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, getResources().getColor(R.color.category_colors));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set what happens when we click on an Item in the list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Ensure that the media resources have been freed up first
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream
                        AudioManager.STREAM_MUSIC,
                        // Request temporary focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Initialise the MediaPlayer with the correct Miwok audio
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,
                                                      words.get(i).getAudioID());

                    // Play the audio file
                    mMediaPlayer.start();

                    // Setup our custom OnCompletionListener onto the media player
                    // so that we can free up some resources
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {

        // Call the super class onStop method
        super.onStop();

        // Release the media player resources
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon audio focus back to the system
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}