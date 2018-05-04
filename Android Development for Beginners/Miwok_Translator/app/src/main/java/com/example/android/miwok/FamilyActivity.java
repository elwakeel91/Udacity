package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * {@link FamilyActivity} is the activity for the Family category
 */
public class FamilyActivity extends AppCompatActivity {

    /**
     * Creates the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set up the Family Category fragment
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, new FamilyFragment())
                                   .commit();
    }
}
