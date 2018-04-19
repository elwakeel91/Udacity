package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;                                             // Tracks the score for Team A
    int scoreTeamB = 0;                                             // Tracks the score for Team B

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display the scores on the screen
        displayForTeamA();
        displayForTeamB();
    }

    /**
     * Display the score for Team A
     */
    private void displayForTeamA()
    {
        TextView pointsText = findViewById(R.id.team_A_score);
        pointsText.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Increase the score of Team A by 3
     */
    public void addThreeForTeamA(View view)
    {
        scoreTeamA += 3;
        displayForTeamA();
    }

    /**
     * Increase the score of Team A by 2
     */
    public void addTwoForTeamA(View view)
    {
        scoreTeamA += 2;
        displayForTeamA();
    }

    /**
     * Increase the score of Team A by 1
     */
    public void addOneForTeamA(View view)
    {
        scoreTeamA++;
        displayForTeamA();
    }

    /**
     * Display the score for Team B
     */
    private void displayForTeamB()
    {
        TextView pointsText = findViewById(R.id.team_B_score);
        pointsText.setText(String.valueOf(scoreTeamB));
    }

    /**
     * Increase the score of Team B by 3
     */
    public void addThreeForTeamB(View view)
    {
        scoreTeamB += 3;
        displayForTeamB();
    }

    /**
     * Increase the score of Team B by 2
     */
    public void addTwoForTeamB(View view)
    {
        scoreTeamB += 2;
        displayForTeamB();
    }

    /**
     * Increase the score of Team B by 1
     */
    public void addOneForTeamB(View view)
    {
        scoreTeamB++;
        displayForTeamB();
    }

    /**
     * Reset the scores
     */
    public void reset(View view)
    {
        // Set the score for both teams to zero
        scoreTeamA = 0;
        scoreTeamB = 0;
        // Update the score board
        displayForTeamA();
        displayForTeamB();
    }
}
