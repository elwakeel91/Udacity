package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link NumbersActivity} is the activity for the Numbers category
 */
public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        // Create an ArrayList of {@link Word} objects to store the numbers
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.number_one,"One", "Litta"));
        words.add(new Word(R.drawable.number_two,"Two", "Otiiko"));
        words.add(new Word(R.drawable.number_three,"Three", "Tolooskosu"));
        words.add(new Word(R.drawable.number_four,"Four", "Oyyisa"));
        words.add(new Word(R.drawable.number_five,"Five", "Massokka"));
        words.add(new Word(R.drawable.number_six,"Six", "Temmokka"));
        words.add(new Word(R.drawable.number_seven,"Seven", "Kenekaku"));
        words.add(new Word(R.drawable.number_eight,"Eight", "Kawinta"));
        words.add(new Word(R.drawable.number_nine,"Nine", "Wo'e"));
        words.add(new Word(R.drawable.number_ten,"Ten", "Na'aacha"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
    }
}

