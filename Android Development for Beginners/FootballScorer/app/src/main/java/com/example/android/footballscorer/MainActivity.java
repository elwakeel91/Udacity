package com.example.android.footballscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;                     // The score for Team A
    private int scoreTeamB = 0;                     // The score for Team B

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goalA (View view) {

        // Get the EditText object that has the name of the scorer
        EditText editText = findViewById(R.id.player_name_team_A);

        // If the text field is empty, don't do anything
        if (editText.getText().toString().equals(""))
            return;

        // Update the ScoreBoard
        scoreTeamA++;
        TextView score = findViewById(R.id.score_team_A);
        score.setText(String.valueOf(scoreTeamA));

        // Get the name of the scorer from the EditText view
        String name = editText.getText().toString();
        // Remove the name from the EditText view
        editText.setText("");

        // Add a goal to the Team score board
        addGoal(view, name);
    }

    public void goalB (View view) {

        // Get the EditText object that has the name of the scorer
        EditText editText = findViewById(R.id.player_name_team_B);

        // If the text field is empty, don't do anything
        if (editText.getText().toString().equals(""))
            return;

        // Update the ScoreBoard
        scoreTeamB++;
        TextView score = findViewById(R.id.score_team_B);
        score.setText(String.valueOf(scoreTeamB));

        // Get the name of the scorer from the EditText view
        String name = editText.getText().toString();
        // Remove the name from the EditText view
        editText.setText("");

        // Add a goal to the Team score board
        addGoal(view, name);
    }

    public void reset (View view){
        EditText teamName = findViewById(R.id.team_name_team_A);
        teamName.setText("");

        scoreTeamA = 0;
        TextView score = findViewById(R.id.score_team_A);
        score.setText(String.valueOf(scoreTeamA));

        LinearLayout teamLayout = findViewById(R.id.layout_team_A);
        int goalIndex = teamLayout.indexOfChild(findViewById(R.id.goal_team_A));

        while (teamLayout.getChildCount() > goalIndex + 1){
            teamLayout.removeViewAt(goalIndex + 1);
        }

        teamName = findViewById(R.id.team_name_team_B);
        teamName.setText("");

        scoreTeamB = 0;
        score = findViewById(R.id.score_team_B);
        score.setText(String.valueOf(scoreTeamB));

        teamLayout = findViewById(R.id.layout_team_B);
        goalIndex = teamLayout.indexOfChild(findViewById(R.id.goal_team_B));

        while (teamLayout.getChildCount() > goalIndex + 1){
            teamLayout.removeViewAt(goalIndex + 1);
        }
    }

    private void addGoal(View view, String name)
    {
        // Set the parent as the Team Linear Layout
        LinearLayout teamLayout = (LinearLayout) view.getParent();

        // Create a new custom view using the scorer_layout.xml file and make it a child of the Main Layout
        View scorerView = LayoutInflater.from(this).inflate(R.layout.scorer_layout, teamLayout, false);
        // Find the TextView holding the name of the scorer
        TextView nameView = scorerView.findViewById(R.id.name);
        // Set the name
        nameView.setText(name);

        // Add the custom view to the Main Layout
        teamLayout.addView(scorerView);
    }
}
