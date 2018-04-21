package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app creates an order email to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 1;                                   // The order quantity

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Display the default quantity
         displayQuantity();
    }

    /**
     * This method is called when the '+' button is clicked
     */
    public void AddOne(View view) {
        // Limit to 100 cups of coffee
        if (quantity < 100)
        {
            // Increase the quantity by one
            quantity++;
            // Display the new quantity
            displayQuantity();
            // End the method
        }
        else // Tell the user they're ordering too many cups
            toastMessage("Nobody's that thirsty...");
    }

    /**
     * This method is called when the '-' button is clicked
     */
    public void MinusOne(View view) {
        // Make sure the quantity doesn't go below one
        if (quantity > 1)
        {
            // Reduce the quantity by one
            quantity--;
            // Display the new quantity
            displayQuantity();
            // Get out of the method
        }
        else
            // Tell the user he can't
            toastMessage("You can't order fewer cups!");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Grab the customer's name
        TextView textView = findViewById(R.id.name);
        String name = textView.getText().toString();

        // Error check if the name is empty
        if (name.equals(""))
            // Print an error message if the customer hasn't entered their name
            toastMessage("We need a name for this delicious order!");
        else
        {   // Send an email with the order summary
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for " + name);
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                toastMessage("Sending message..");
            }
            else
                toastMessage("Couldn't find email messaging app...");
        }
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
     * @param hasWhippedCream if the order includes whipped cream
     * @param hasChocolate if the order includes chocolate
     * @return number of cups of coffee x price per cup
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        // Set the price of 1 cup of coffee
        float price = Float.parseFloat(getString(R.string.base_price));

        // Add the price of whipped cream if the user included it
        if (hasWhippedCream)
            price += Float.parseFloat(getString(R.string.whipped_cream_price));

        // Add the price of chocolate if the user included it
        if (hasChocolate)
            price += Float.parseFloat(getString(R.string.chocolate_price));

        // Calculate the price of the order
        return quantity * price;
    }

    /**
     * Creates the order summary.
     * @return the order summary
     */
    @NonNull
    private String createOrderSummary() {
        // Get the customer's name
        EditText editText = findViewById(R.id.name);
        String name = editText.getText().toString();

        // Check if the order includes whipped cream
        CheckBox whippedCream = findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCream.isChecked();

        // Check if the order includes chocolate
        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        // Calculate the price
        float price = calculatePrice(hasWhippedCream, hasChocolate);
        // Get the local currency
        String localCurrency = getString(R.string.local_currency);

        return  getString(R.string.order_summary_name, name) + "\n" +
                getString(R.string.order_summary_whipped_cream, hasWhippedCream) + "\n" +
                getString(R.string.order_summary_chocolate, hasChocolate) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_price, localCurrency, price) + "\n" +
                getString(R.string.thank_you);
    }

    private void toastMessage(String errorMessage)
    {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}