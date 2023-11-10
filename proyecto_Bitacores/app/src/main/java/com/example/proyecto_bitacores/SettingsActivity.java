
package com.example.proyecto_bitacores;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Obtener las preferencias compartidas
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Obtener el nombre de usuario de SharedPreferences
        String username = preferences.getString("username", null);
        String password = preferences.getString("password", null);

        // Mostrar el nombre de usuario en el TextView
        TextView tvUsername = findViewById(R.id.tvUserName);
        TextView tvPassword = findViewById(R.id.tvPassword);

        if (username != null) {
            tvUsername.setText("Username: " + username);
            tvPassword.setText("Password: " + password);

        } else {
            tvUsername.setText("No se encontraron datos de usuario.");
        }
        Button btnSettings = findViewById(R.id.btnMenu);
        View settingsButtonView = getLayoutInflater().inflate(R.layout.activity_settings, null);


        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
            }
        });
        // Obtener la referencia al ImageView
        ImageView imageViewUser = findViewById(R.id.imageViewUser);

        // Cargar la imagen en el ImageView
        imageViewUser.setImageResource(R.drawable.user); // Asegúrate de tener la imagen user.jpg en la carpeta res/drawable
    }
}
