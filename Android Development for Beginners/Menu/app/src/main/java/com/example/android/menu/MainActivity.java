package com.example.android.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView textView = findViewById(R.id.menu_item_1);
        String message = textView.getText().toString();
        Log.i("MainActivity.java", message);

        // Find second menu item TextView and print the text to the logs
        textView = findViewById(R.id.menu_item_2);
        message = textView.getText().toString();
        Log.i("MainActivity.java", message);

        // Find third menu item TextView and print the text to the logs
        textView = findViewById(R.id.menu_item_3);
        message = textView.getText().toString();
        Log.i("MainActivity.java", message);
    }
}
