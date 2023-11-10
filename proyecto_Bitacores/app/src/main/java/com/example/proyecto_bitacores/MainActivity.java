package com.example.proyecto_bitacores;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.proyecto_bitacores.R;

import com.example.proyecto_bitacores.LoginActivity;
import com.example.proyecto_bitacores.MenuActivity;
import com.example.proyecto_bitacores.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que esto está presente y configurado correctamente

        // Infla el diseño de product_list_item.xml
        View productListItemView = getLayoutInflater().inflate(R.layout.product_list_item, null);
        ImageView productImageView = productListItemView.findViewById(R.id.productImageView);

        try (MyDatabaseManager dbManager = new MyDatabaseManager(this)) {
            // Operaciones con la base de datos aquí

            // Ahora puedes configurar la imagen en el ImageView
            Drawable productDrawable = ContextCompat.getDrawable(this, R.drawable.product1);
            productImageView.setImageDrawable(productDrawable);

            dbManager.insertProduct("Iphone", 1000, "product1.jpg");
            dbManager.insertProduct("Portatil", 800, "product2.jpg");
            dbManager.insertProduct("Fifa 23", 40, "product3.jpg");
            dbManager.insertProduct("Play 5", 599, "product4.jpg");
            dbManager.insertProduct("mando Play 5", 60, "product1.jpg");
            dbManager.insertProduct("Zapatillas nike", 70, "product1.jpg");

        } catch (Exception e) {
            // Manejar la excepción, si es necesario
            e.printStackTrace();
        }
        // Verificar si el usuario ya ha iniciado sesión
        if (userIsLoggedIn()) {
            // Si el usuario ya ha iniciado sesión, dirigirlo a la pantalla del menú
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            // Si el usuario no ha iniciado sesión, dirigirlo a la pantalla de login
            startActivity(new Intent(this, LoginActivity.class));
        }

        // Finalizar la actividad actual para que el usuario no pueda regresar a la pantalla de inicio
        finish();
    }

    // Método de ejemplo para verificar si el usuario ya ha iniciado sesión (puedes implementar tu propia lógica)
    private boolean userIsLoggedIn() {
        // Aquí puedes implementar la lógica para verificar si el usuario ha iniciado sesión, por ejemplo, consultando la base de datos.
        return false; // En este ejemplo, siempre se considera que el usuario no ha iniciado sesión.
    }
}
