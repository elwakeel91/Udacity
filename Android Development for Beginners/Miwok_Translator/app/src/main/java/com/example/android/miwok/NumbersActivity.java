package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * {@link NumbersActivity} is the activity for the Numbers category
 */
public class NumbersActivity extends AppCompatActivity {

    /**
     * Creates the Numbers activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set up the Numbers Category fragment
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, new NumbersFragment())
                                   .commit();
    }
}