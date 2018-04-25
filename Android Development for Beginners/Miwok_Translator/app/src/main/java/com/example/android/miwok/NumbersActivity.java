package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link NumbersActivity} is the activity for the Numbers category
 */
public class NumbersActivity extends AppCompatActivity {

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

        // Create an ArrayList of {@link Word} objects to store the numbers
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.number_one,"One", "Lutti", R.raw.number_one));
        words.add(new Word(R.drawable.number_two,"Two", "Otiiko", R.raw.number_two));
        words.add(new Word(R.drawable.number_three,"Three", "Tolooskosu", R.raw.number_three));
        words.add(new Word(R.drawable.number_four,"Four", "Oyyisa", R.raw.number_four));
        words.add(new Word(R.drawable.number_five,"Five", "Massokka", R.raw.number_five));
        words.add(new Word(R.drawable.number_six,"Six", "Temmokka", R.raw.number_six));
        words.add(new Word(R.drawable.number_seven,"Seven", "Kenekaku", R.raw.number_seven));
        words.add(new Word(R.drawable.number_eight,"Eight", "Kawinta", R.raw.number_eight));
        words.add(new Word(R.drawable.number_nine,"Nine", "Wo'e", R.raw.number_nine));
        words.add(new Word(R.drawable.number_ten,"Ten", "Na'aacha", R.raw.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter =
                new WordAdapter(this, words, getResources().getColor(R.color.category_numbers));

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

                // Initialise the MediaPlayer with the correct Miwok audio
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getAudioID());

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