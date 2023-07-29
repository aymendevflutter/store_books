package com.example.aymen;

import static com.example.aymen.fullprice.price.fullprice;
import static com.example.aymen.fullprice.price.itemNamesx;
import static com.example.aymen.fullprice.price.pricesArray;
import static com.example.aymen.fullprice.price.quantitiesx;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aymen.fullprice.price;
import com.example.aymen.objects.CartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button clearButton;
    private TextView Fullprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tableLayout = findViewById(R.id.tableLayout);
        clearButton = findViewById(R.id.clearButton);
        Fullprice = findViewById(R.id.showtotal);
        double number = fullprice;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(number);

        String showprice = "Toltal :" + formattedNumber +"$";
        Fullprice.setText( showprice);

        // Retrieve the ArrayList from the previous activity
        Intent intent = getIntent();





        ArrayList<String> itemNames =itemNamesx;
                //intent.getStringArrayListExtra("i");
                //new ArrayList<>(Arrays.asList("aymen", "ahmed", "samir"));
        ArrayList<Integer> quantities = quantitiesx;
        List<Double> prices = pricesArray;
        //intent.getIntegerArrayListExtra("q");
                //new ArrayList<>(Arrays.asList(10, 20, 25, 14));

      //  double[] pricesArray =
                //intent.getDoubleArrayExtra("p");

     //   List<Double> prices = null;
     //   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
     //       prices = Arrays.stream(pricesArray)
      //              .boxed()
      //              .collect(Collectors.toList());
      //  }

        //new ArrayList<>(Arrays.asList(10.5, 20.2, 30.5));

        // Merge quantities for duplicate items
        Map<String, CartItem> cartItems = new HashMap<>();
        for (int i = 0; i < itemNames.size(); i++) {
            String itemName = itemNames.get(i);
            int quantity = quantities.get(i);
            double price = prices.get(i);

            if (cartItems.containsKey(itemName)) {
                CartItem cartItem = cartItems.get(itemName);
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                CartItem cartItem = new CartItem(itemName, quantity, price);
                cartItems.put(itemName, cartItem);
            }
        }

        // Convert map values to a list
        List<CartItem> itemList = new ArrayList<>(cartItems.values());

        // Add header row
        TableRow headerRow = new TableRow(this);

        TextView headerNameTextView = new TextView(this);
        headerNameTextView.setText("Item name");
        headerNameTextView.setTextSize(16);
        headerNameTextView.setWidth(250); // Set fixed width
        headerNameTextView.setHeight(80); // Set fixed height
        headerNameTextView.setTextColor(Color.BLACK);
        headerNameTextView.setTypeface(null, Typeface.BOLD);
        headerRow.addView(headerNameTextView);

        TextView headerQuantityTextView = new TextView(this);
        headerQuantityTextView.setText("Num");
        headerQuantityTextView.setTextSize(16);
        headerQuantityTextView.setWidth(150); // Set fixed width
        headerQuantityTextView.setHeight(80); // Set fixed height
        headerQuantityTextView.setTextColor(Color.BLACK);
        headerQuantityTextView.setTypeface(null, Typeface.BOLD);
        headerRow.addView(headerQuantityTextView);

        TextView headerPriceTextView = new TextView(this);
        headerPriceTextView.setText("$");
        headerPriceTextView.setTextSize(16);
        headerPriceTextView.setWidth(150); // Set fixed width
        headerPriceTextView.setHeight(80); // Set fixed height
        headerPriceTextView.setTextColor(Color.BLACK);
        headerPriceTextView.setTypeface(null, Typeface.BOLD);
        headerRow.addView(headerPriceTextView);

        TextView headerTotalTextView = new TextView(this);
        headerTotalTextView.setText("Total");
        headerTotalTextView.setTextSize(16);
        headerTotalTextView.setWidth(150); // Set fixed width
        headerTotalTextView.setHeight(80); // Set fixed height
        headerTotalTextView.setTextColor(Color.BLACK);
        headerTotalTextView.setTypeface(null, Typeface.BOLD);
        headerRow.addView(headerTotalTextView);

        tableLayout.addView(headerRow);

        // Populate the table with rows dynamically
        for (int i = 0; i < itemList.size(); i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            row.setLayoutParams(params);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(itemList.get(i).getName());
            nameTextView.setTextSize(16);
            nameTextView.setWidth(250); // Set fixed width
            nameTextView.setHeight(80); // Set fixed height
            nameTextView.setTextColor(Color.BLACK);
            nameTextView.setSingleLine(true); // Display the text in a single line
            nameTextView.setEllipsize(TextUtils.TruncateAt.END); // Add ellipsis at the end of truncated text
            row.addView(nameTextView);


            TextView quantityTextView = new TextView(this);
            quantityTextView.setText(String.valueOf(itemList.get(i).getQuantity()));
            quantityTextView.setTextSize(16);
            quantityTextView.setWidth(150); // Set fixed width
            quantityTextView.setHeight(80); // Set fixed height
            quantityTextView.setTextColor(Color.BLACK);
            row.addView(quantityTextView);

            TextView priceTextView = new TextView(this);
            priceTextView.setText(String.valueOf(itemList.get(i).getPrice()));
            priceTextView.setTextSize(16);
            priceTextView.setWidth(150); // Set fixed width
            priceTextView.setHeight(80); // Set fixed height
            priceTextView.setTextColor(Color.BLACK);
            row.addView(priceTextView);

            TextView totalTextView = new TextView(this);
            totalTextView.setText(String.valueOf(itemList.get(i).getTotal()));
            totalTextView.setTextSize(16);
            totalTextView.setWidth(150); // Set fixed width
            totalTextView.setHeight(80); // Set fixed height
            totalTextView.setTextColor(Color.BLACK);
            row.addView(totalTextView);

            if (i == 0) {
             //   nameTextView.setTypeface(null, Typeface.BOLD);
             //   quantityTextView.setTypeface(null, Typeface.BOLD);
              //  priceTextView.setTypeface(null, Typeface.BOLD);
              //  totalTextView.setTypeface(null, Typeface.BOLD);
            }

            tableLayout.addView(row);
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all rows from the table
                tableLayout.removeAllViews();
                price.fullprice  =0.0 ;

                itemNamesx =new ArrayList<>() ;

                quantitiesx = new ArrayList<>();

                pricesArray = new ArrayList<>() ;


                // TODO: Update the total number back to the default value
            }
        });
    }
}
