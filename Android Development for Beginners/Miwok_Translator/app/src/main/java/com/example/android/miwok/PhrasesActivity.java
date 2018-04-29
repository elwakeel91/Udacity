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
 * {@link PhrasesActivity} is the activity for our Phrases activity
 */
public class PhrasesActivity extends AppCompatActivity {

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

    // Create a custom audio focus change listener
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    // Check if we have lost audio focus permanently
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS){

                        // Stop the audio and release the media resources
                        releaseMediaPlayer();
                    }

                    // Check if we have lost audio focus temporarily
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                        // Pause the audio file
                        mMediaPlayer.pause();
                    }

                    // Check if we have regained audio focus
                    if (focusChange == AudioManager.AUDIOFOCUS_GAIN){

                        // Reset the audio to start from the beginning
                        mMediaPlayer.seekTo(0);

                        // Play the audio once more
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
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, getResources().getColor(R.color.category_phrases));

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
                                                             // Use the music audio stream
                                                             AudioManager.STREAM_MUSIC,
                                                             // Request temporary audio focus
                                                             AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // If the request has been granted
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Initialise the MediaPlayer with the correct Miwok audio
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,
                                                      words.get(i).getAudioID());

                    // Play the audio
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