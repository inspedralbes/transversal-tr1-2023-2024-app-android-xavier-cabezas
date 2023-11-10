package com.example.proyecto_bitacores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Obtén la lista de todos los productos
        try (MyDatabaseManager dbManager = new MyDatabaseManager(this)) {
            // Operaciones con la base de datos aquí
            products = dbManager.getAllProducts();
        } catch (Exception e) {
            // Manejar la excepción, si es necesario
            e.printStackTrace();
        }

        // Configura el evento de clic en un producto
        ListView listView = findViewById(R.id.listView);
        ProductAdapter adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);
// ... (tu código existente)



// Configurar el evento de clic en un producto
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el producto seleccionado
                Product selectedProduct = products.get(position);

                // Inflar el diseño del cuadro de diálogo
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_to_cart, null);

                // Configurar la información del producto en el cuadro de diálogo
                TextView productNameTextView = dialogView.findViewById(R.id.productNameTextView);
                productNameTextView.setText(selectedProduct.getName());

                // Construir el cuadro de diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setView(dialogView);

                // Mostrar el cuadro de diálogo
                AlertDialog dialog = builder.create();

                // Configurar el evento de clic en el botón "Confirmar"
                Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Añadir el producto al carrito
                        // Aquí puedes agregar la lógica para agregar el producto al carrito de la compra
                        // Por ejemplo, llamar a tu método addToCart(selectedProduct)

                        // Cerrar el cuadro de diálogo
                        dialog.dismiss();

                        // Mostrar un aviso de que se añadió el producto al carrito
                        mostrarAviso("Producto añadido al carrito: " + selectedProduct.getName());
                    }
                });

                // Mostrar el cuadro de diálogo
                dialog.show();
            }
        });

// Botón para ir al carrito
        Button btnGoToCart = findViewById(R.id.btnGoToCart);
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la pantalla del carrito de compras
                startActivity(new Intent(MenuActivity.this, ShoppingCartActivity.class));
            }
        });
        // Dentro de tu método onCreate en MenuActivity
        Button btnSettings = findViewById(R.id.btnSettings);
        View settingsButtonView = getLayoutInflater().inflate(R.layout.menu_settings_button, null);



        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
            }
        });




    }

    // Método para abrir la pantalla del carrito de la compra
    private void openShoppingCart() {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }


    // Método para mostrar un aviso
    private void mostrarAviso(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
