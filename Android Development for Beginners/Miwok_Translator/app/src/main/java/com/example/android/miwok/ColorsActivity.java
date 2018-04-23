package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link ColorsActivity} is the activity for the Colors category
 */
public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        // Create a list of words
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.drawable.color_red,"red", "Weṭeṭṭi"));
        words.add(new Word(R.drawable.color_mustard_yellow,"mustard yellow", "Chiwiiṭә"));
        words.add(new Word(R.drawable.color_dusty_yellow,"dusty yellow", "Topiisә"));
        words.add(new Word(R.drawable.color_green,"green", "chokokki"));
        words.add(new Word(R.drawable.color_brown,"brown", "ṭakaakki"));
        words.add(new Word(R.drawable.color_gray,"gray", "ṭopoppi"));
        words.add(new Word(R.drawable.color_black,"black", "kululli"));
        words.add(new Word(R.drawable.color_white,"white", "kelelli"));

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
    }
}
