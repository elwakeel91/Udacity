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
    private int quantity = 2;                                   // The order quantity
    private float price = 5.0f;                                 // The price of one cup of coffee
    private String name = "Kareem El-Wakeel";                   // The customer's name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display the default quantity
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
        // Display the order summary
        displayOrderSummary();
    }

    /**
     * Displays the order quantity on the screen.
     */
    private void displayQuantity() {
        // Send the order quantity to the display
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(quantity));
    }

    /**
     * Calculates the price of the order
     */
    private float calculatePrice() {
        return quantity * price;
    }

    /**
     * Creates the order summary.
     */
    private String createOrderSummary() {
        return "Name: " + name + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + String.format("£%.2f", calculatePrice()) + "\n" +
                "Thank you!";
    }

    /**
     * Displays the order summary on the screen.
     */
    private void displayOrderSummary() {
        // Send the order summary to the display
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(createOrderSummary());
    }
}