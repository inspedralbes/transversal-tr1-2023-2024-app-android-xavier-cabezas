package com.example.proyecto_bitacores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CartProductAdapter extends ArrayAdapter<Product> {

    private Context context;

    public CartProductAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false);
        }

        // Obtener el producto en la posición actual
        Product product = getItem(position);

        // Configurar la información del producto en las vistas del diseño
        if (product != null) {
            ImageView productImageView = convertView.findViewById(R.id.cartProductImageView);
            TextView productNameTextView = convertView.findViewById(R.id.cartProductNameTextView);
            TextView productPriceTextView = convertView.findViewById(R.id.cartProductPriceTextView);

            // Configurar la imagen, nombre y precio del producto
            productImageView.setImageResource(product.getImageResourceId());
            productNameTextView.setText(product.getName());
            productPriceTextView.setText("Price: $" + product.getPrice());
        }

        return convertView;
    }
}
