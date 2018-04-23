/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view that shows the Family category
        View family = findViewById(R.id.family);
        // Set a click listener onto that view
        family.setOnClickListener(new View.OnClickListener() {
            // This method is called when the view is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@Link FamilyActivity)
                Intent familyIntent = new Intent(MainActivity.this,
                                                 FamilyActivity.class);
                // Start the new activity
                startActivity(familyIntent);
            }
        });

        // Find the view that shows the Numbers category
        View numbers = findViewById(R.id.numbers);
        // Set a click listener onto that view
        numbers.setOnClickListener(new View.OnClickListener() {
            // This method is called when the view is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@Link NumbersActivity)
                Intent numbersIntent = new Intent(MainActivity.this,
                                                  NumbersActivity.class);
                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        // Find the view that shows the Colors category
        View colors = findViewById(R.id.colors);
        // Set a click listener onto that view
        colors.setOnClickListener(new View.OnClickListener() {
            // This method is called when the view is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@Link ColorsActivity)
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                // Start the new activity
                startActivity(colorsIntent);
            }
        });

        // Find the view that shows the Phrases category
        View phrases = findViewById(R.id.phrases);
        // Set a click listener onto that view
        phrases.setOnClickListener(new View.OnClickListener() {
            // This method is called when the view is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@Link PhrasesActivity)
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                // Start the new activity
                startActivity(phrasesIntent);
            }
        });
    }
}
