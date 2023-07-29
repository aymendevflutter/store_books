package com.example.aymen;

import static com.example.aymen.fullprice.price.fullprice;
import static com.example.aymen.fullprice.price.itemNamesx;
import static com.example.aymen.fullprice.price.pricesArray;

import static com.example.aymen.fullprice.price.quantitiesx;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ImageView itemImageView;
    private TextView descriptionTextView;
    private TextView authorTextView;
    private TextView priceTextView;
    private EditText quantityEditText;
    private Button addItemButton;
    private TextView TitleView;

    private int itemPicture;
    private String itemDescription;
    private String itemAuthor;
    private String title;
    private String categ;
    private double itemPrice;
    private List<Integer> quntity;
    private List<Double>   price;
    private  List<Double>total;
   private  List<String>  name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        quntity = new ArrayList<>();
        price =new ArrayList<>();
        total =new ArrayList<>();
        name = new ArrayList<>();
        int t;

        // Retrieve item details from the clicked item in the ListView
        itemPicture = getIntent().getIntExtra("item_picture",12);
        itemDescription = getIntent().getStringExtra("desc");
        itemAuthor = getIntent().getStringExtra("item_author");
        itemPrice = getIntent().getDoubleExtra("item_price", 0.0);
        title = getIntent().getStringExtra("title");
        categ =getIntent().getStringExtra("categ");

        // Initialize views
        itemImageView = findViewById(R.id.item_image_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        authorTextView = findViewById(R.id.author_text_view);
        priceTextView = findViewById(R.id.price_text_view);
        quantityEditText = findViewById(R.id.quantity);
        addItemButton = findViewById(R.id.add_item_button);
        TitleView = findViewById(R.id.titlebook);


        // Set item details to views
        // Use appropriate methods to load image into the itemImageView
        descriptionTextView.setText(itemDescription);
        authorTextView.setText(itemAuthor);
        priceTextView.setText(String.valueOf(itemPrice));
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), itemPicture);
        Bitmap resizedBitmap = resizeImage(originalBitmap, 500, 500);


        itemImageView.setImageBitmap(resizedBitmap);
       // itemImageView.setImageResource(itemPicture);
        TitleView.setText(title);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityString = quantityEditText.getText().toString().trim();
                if (quantityString.isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
                    return;
                }


                int quantity = Integer.parseInt(quantityString);
                double totalPrice = itemPrice * quantity;
                fullprice +=totalPrice;
                name.add(title);
                price.add(itemPrice);
                total.add(totalPrice);
                quntity.add(quantity);
                itemNamesx.add(title);
                quantitiesx.add(quantity) ;
                pricesArray.add(itemPrice);




                Toast.makeText(DetailActivity.this, "add with succefull", Toast.LENGTH_SHORT).show();
                getDataForBack();



            }
        });
    }
    public Bitmap resizeImage(Bitmap originalBitmap, int newWidth, int newHeight) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();

        // Calculate the scaling factors
        float scaleX = (float) newWidth / originalWidth;
        float scaleY = (float) newHeight / originalHeight;

        // Create a matrix for the scaling operation
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        // Resize the original bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalWidth, originalHeight, matrix, true);

        return resizedBitmap;
    }
    @Override
    public void onBackPressed() {
        getDataForBack();

    }
    void getDataForBack(){
        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra("categ",categ);
      //  intent.putStringArrayListExtra("i",(ArrayList<String>) name);
      //  intent.putIntegerArrayListExtra("q",(ArrayList<Integer>) quntity);

      //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      //      intent.putExtra("p", price.stream().mapToDouble(Double::doubleValue).toArray());
      //  }
        startActivity(intent);
        finish();
    }





}
