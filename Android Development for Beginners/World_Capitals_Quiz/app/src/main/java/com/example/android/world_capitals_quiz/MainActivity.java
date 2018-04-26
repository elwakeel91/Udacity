package com.example.android.world_capitals_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Initialise the isChecking variable to use with RadioButtons
    private boolean isChecking = true;

    // Initialise the user's score
    private int score = 0;
    // Initialise hasAnswered to false as the user hasn't played yet
    private boolean hasAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the system notifications while the user doesn't need it
        View decorView = this.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        decorView.setSystemUiVisibility(uiOptions);

        // Join the RadioGroups for each country so they're respective Radio Buttons work together
        //
        // Russia //
        RadioGroup russiaFirstGroup = findViewById(R.id.russia_first_group);
        RadioGroup russiaSecondGroup = findViewById(R.id.russia_second_group);
        joinRadioGroups(russiaFirstGroup, russiaSecondGroup);
        //
        // Indonesia //
        RadioGroup indonesiaFirstGroup = findViewById(R.id.indonesia_first_group);
        RadioGroup indonesiaSecondGroup = findViewById(R.id.indonesia_second_group);
        joinRadioGroups(indonesiaFirstGroup, indonesiaSecondGroup);
        //
        // Canada //
        RadioGroup canadaFirstGroup = findViewById(R.id.canada_first_group);
        RadioGroup canadaSecondGroup = findViewById(R.id.canada_second_group);
        joinRadioGroups(canadaFirstGroup, canadaSecondGroup);
        //
        // Mexico //
        RadioGroup mexicoFirstGroup = findViewById(R.id.mexico_first_group);
        RadioGroup mexicoSecondGroup = findViewById(R.id.mexico_second_group);
        joinRadioGroups(mexicoFirstGroup, mexicoSecondGroup);

        // Get the submit button
        Button submit = findViewById(R.id.submit);
        // Handle what happens when we click the submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate the score
                calculateScore();
                // Create a message that includes the score
                String message = "Your score is " + score;
                // Display a Toast containing the message
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Joins the two RadioGroups so that the RadioButtons in each group react to each other
     * @param firstGroup is the first RadioGroup
     * @param secondGroup is the second RadioGroup
     */
    private void joinRadioGroups(final RadioGroup firstGroup, final RadioGroup secondGroup) {

        // Create a custom Listener to check when a Radio button within the first group is checked
        firstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If we are checking a button in the first group
                if (checkedId != -1 && isChecking) {
                    // Set isChecking to false so we don't enter the if statement in the other listener
                    isChecking = false;
                    // Clear the buttons in the second group
                    secondGroup.clearCheck();
                }
                // Set isChecking back to true
                isChecking = true;
            }
        });

        // Create a custom Listener to check when a Radio button within the second group is checked
        secondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // If we are checking a button in the second group
                if (checkedId != -1 && isChecking) {
                    // Set isChecking to false so we don't enter the if statement in the other listener
                    isChecking = false;
                    // Clear the buttons in the first group
                    firstGroup.clearCheck();
                }
                // Set isChecking back to true
                isChecking = true;
            }
        });
    }

    /**
     * Calculates the total score of the quiz
     * @return the value of the score
     */
    private void calculateScore(){

        // Do nothing if the user has submitted an answer already
        if (hasAnswered) return;

        // Get the Moscow RadioButton
        RadioButton moscow = findViewById(R.id.moscow);
        // Set the correct answer and score
        setCorrectAnswer(moscow);

        // Get the Jakarta RadioButton
        RadioButton jakarta = findViewById(R.id.jakarta);
        // Set the correct answer and score
        setCorrectAnswer(jakarta);

        // Get the Ottowa RadioButton
        RadioButton ottowa = findViewById(R.id.ottowa);
        // Set the correct answer and score
        setCorrectAnswer(ottowa);

        // Get the Mexico City RadioButton
        RadioButton mexicoCity = findViewById(R.id.mexico_city);
        // Set the correct answer and score
        setCorrectAnswer(mexicoCity);

        // Stop the user from answering again
        hasAnswered = true;
    }

    private void setCorrectAnswer(RadioButton correctButton) {
        // Check if the user checked the correct button
        if (correctButton.isChecked()) {
            // Add one to the score
            score++;
            // Change the button color to the primary color to highlight it the correct answer
            correctButton.setButtonTintList(MainActivity.this.getResources().getColorStateList(R.color.colorPrimary));
            correctButton.performClick();
        }
        else { // Change the button color to red to highlight they answered wrong
            correctButton.setButtonTintList(MainActivity.this.getResources().getColorStateList(R.color.red));
            correctButton.performClick();
        }
    }
}
