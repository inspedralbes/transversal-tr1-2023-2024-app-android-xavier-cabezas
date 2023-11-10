package com.example.proyecto_bitacores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private ArrayList<Product> cartProducts;
    private CartProductAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Obtener la información del intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedProduct")) {
            Product selectedProduct = (Product) intent.getSerializableExtra("selectedProduct");

            // Agregar el producto al carrito
            addToCart(selectedProduct);
        }
        Button btnSettings = findViewById(R.id.btnMenu);
        View settingsButtonView = getLayoutInflater().inflate(R.layout.menu_settings_button, null);


        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingCartActivity.this, MenuActivity.class));
            }
        });
        // Configurar el ListView y el adaptador
        ListView cartListView = findViewById(R.id.cartListView);
        cartProducts = new ArrayList<>();
        cartAdapter = new CartProductAdapter(this, cartProducts);
        cartListView.setAdapter(cartAdapter);
    }

    // Método para agregar productos al carrito
    private void addToCart(Product product) {
        if (product != null) {
            cartProducts.add(product);
            cartAdapter.notifyDataSetChanged();
        }
    }
}

