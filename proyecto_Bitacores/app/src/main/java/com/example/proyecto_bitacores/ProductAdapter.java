package com.example.proyecto_bitacores;
import static java.security.AccessController.getContext;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        }

        // Obtener el producto en la posición actual
        Product product = getItem(position);

        // Configurar la información del producto en las vistas del diseño
        if (product != null) {
            ImageView productImageView = convertView.findViewById(R.id.productImageView);
            TextView productNameTextView = convertView.findViewById(R.id.productNameTextView);
            TextView productPriceTextView = convertView.findViewById(R.id.productPriceTextView);

            // Configurar la imagen, nombre y precio del producto
            productImageView.setImageResource(product.getImageResourceId());
            productNameTextView.setText(product.getName());
            productPriceTextView.setText("Price: $" + product.getPrice());
        }

        return convertView;
    }
}
