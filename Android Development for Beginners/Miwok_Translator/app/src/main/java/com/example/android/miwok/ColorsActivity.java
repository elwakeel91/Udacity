package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * {@link ColorsActivity} is the activity for the Colors category
 */
public class ColorsActivity extends AppCompatActivity {

    /**
     * Creates the Colors activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set up the Colors Category fragment
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, new ColorsFragment())
                                   .commit();
    }
}