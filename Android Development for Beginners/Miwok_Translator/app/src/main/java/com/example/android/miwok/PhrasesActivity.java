package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * {@link PhrasesActivity} is the activity for our Phrases activity
 */
public class PhrasesActivity extends AppCompatActivity {

    /**
     * Creates the Phrases activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set up the Phrases Category fragment
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, new PhrasesFragment())
                                   .commit();
    }
}