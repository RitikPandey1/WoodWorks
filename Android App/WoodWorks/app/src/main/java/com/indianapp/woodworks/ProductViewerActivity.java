package com.indianapp.woodworks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductViewerActivity extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView description;
    Button seeIn3d;
    Button buyNow;
    ImageView addCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);
        name=findViewById(R.id.namePv);
        price=findViewById(R.id.pricecH);
        description=findViewById(R.id.descriptionpv);
        seeIn3d=findViewById(R.id.see3);
        buyNow=findViewById(R.id.buypv);
        addCart=findViewById(R.id.addCart);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),FragmentActivity.class);
        startActivity(intent);
        finish();
    }
}