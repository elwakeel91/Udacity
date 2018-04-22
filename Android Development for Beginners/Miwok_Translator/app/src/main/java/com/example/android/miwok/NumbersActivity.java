package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link NumbersActivity} is the activity for the numbers category
 */
public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create an ArrayList of {@link Word} objects to store the numbers
        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("One", "Litta"));
        numbers.add(new Word("Two", "Otiiko"));
        numbers.add(new Word("Three", "Tolooskosu"));
        numbers.add(new Word("Four", "Oyyisa"));
        numbers.add(new Word("Five", "Massokka"));
        numbers.add(new Word("Six", "Temmokka"));
        numbers.add(new Word("Seven", "Kenekaku"));
        numbers.add(new Word("Eight", "Kawinta"));
        numbers.add(new Word("Nine", "Wo'e"));
        numbers.add(new Word("Ten", "Na'aacha"));

        // Create an {@link WordAdapter} to store our words array for a ListView
        WordAdapter wordAdapter = new WordAdapter(this, numbers);
        // Find the ListView in the activity_numbers.xml
        ListView listView = (ListView) findViewById(R.id.list);
        // Attach the numbers WordAdapter to the ListView
        listView.setAdapter(wordAdapter);
    }
}

