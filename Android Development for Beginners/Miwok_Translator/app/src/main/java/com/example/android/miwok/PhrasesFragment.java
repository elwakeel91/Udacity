package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    MediaPlayer mMediaPlayer;                       // Handles playback of all the audio files
    AudioManager mAudioManager;                     // Create an Audio Manager object

    /**
     * A custom 'Audio Focus Change' listener
     */
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
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                        // Pause the audio
                        mMediaPlayer.pause();
                    }

                    // Else check if we have regained audio focus
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                        // Make sure the audio starts from the beginning
                        mMediaPlayer.seekTo(0);
                        // Start the audio
                        mMediaPlayer.start();
                    }
                }
            };

    /**
     * A custom 'Media Completed Playing' listener
     */
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Free up the MediaPlayer resources
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create the {@link rootView} and inflate the words_list.xml layout
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
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

        // Create a {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create 'list item' views for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words,
                                              getResources().getColor(R.color.category_phrases));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

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
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getAudioID());

                    // Play the audio
                    mMediaPlayer.start();

                    // Setup our custom OnCompletionListener onto the media player
                    // so that we can free up some resources
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    /**
     * Handles the end of the fragment
     */
    @Override
    public void onStop() {

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
