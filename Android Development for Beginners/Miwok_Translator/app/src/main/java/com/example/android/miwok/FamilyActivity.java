package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link FamilyActivity} is the activity for the Family category
 */
public class FamilyActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.family_father,"father", "әpә", R.raw.family_father));
        words.add(new Word(R.drawable.family_mother,"mother", "әṭa", R.raw.family_mother));
        words.add(new Word(R.drawable.family_son,"son", "angsi", R.raw.family_son));
        words.add(new Word(R.drawable.family_daughter,"daughter", "tune", R.raw.family_daughter));
        words.add(new Word(R.drawable.family_older_brother,"older brother", "taachi", R.raw.family_older_brother));
        words.add(new Word(R.drawable.family_younger_brother,"younger brother", "chalitti", R.raw.family_younger_brother));
        words.add(new Word(R.drawable.family_older_sister,"older sister", "teṭe", R.raw.family_older_sister));
        words.add(new Word(R.drawable.family_younger_sister,"younger sister", "kolliti", R.raw.family_younger_sister));
        words.add(new Word(R.drawable.family_grandmother,"grandmother ", "ama", R.raw.family_grandmother));
        words.add(new Word(R.drawable.family_grandfather,"grandfather", "paapa", R.raw.family_grandfather));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, getResources().getColor(R.color.category_family));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word}.
        listView.setAdapter(adapter);

        // Set what happens when we click on an Item in the list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Ensure that the media resources have been freed up first
                releaseMediaPlayer();

                // Initialise the MediaPlayer with the correct Miwok audio
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(i).getAudioID());

                // Play the audio
                mMediaPlayer.start();

                // Setup our custom OnCompletionListener onto the media player
                // so that we can free up some resources
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        }
    }
}
