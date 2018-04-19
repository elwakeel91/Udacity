package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 2;
    private float price = 5.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity();
    }

    /**
     * This method is called when the '+' button is clicked
     */
    public void AddOne(View view) {
        // Increase the quantity by one
        quantity++;
        // Display the new quantity
        displayQuantity();
    }

    /**
     * This method is called when the '-' button is clicked
     */
    public void MinusOne(View view) {
        // If the quantity is more than zero
        if (quantity > 0)
            // Reduce the quantity by one
            quantity--;
        // Display the new quantity
        displayQuantity();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        float totalPrice = quantity * price;
        String priceMessage = String.format("£%.2f", totalPrice) + "\nThank you!";
        displayPrice(priceMessage);
    }

    /**
     * This method displays the given quantity on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}