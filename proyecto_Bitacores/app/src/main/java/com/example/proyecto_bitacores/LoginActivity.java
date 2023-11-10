package com.example.proyecto_bitacores;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private MyDatabaseManager databaseManager;

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar la instancia de MyDatabaseManager
        databaseManager = new MyDatabaseManager(this);

        // Referencias a los elementos de la interfaz
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Configurar el botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Validar credenciales
                if (validateCredentials(username, password)) {
                    // Guardar el nombre de usuario en SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", username);
                    editor.apply();

                    // Iniciar la actividad principal o realizar otras acciones después del inicio de sesión
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Configurar el botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Insertar el usuario en la base de datos
                long result = databaseManager.insertUser(username, password);

                if (result != -1) {
                    Toast.makeText(LoginActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para validar las credenciales
    private boolean validateCredentials(String username, String password) {
        // Aquí deberías realizar una consulta a la base de datos para verificar las credenciales
        // Puedes usar el objeto MyDatabaseManager que has creado para interactuar con la base de datos

        MyDatabaseManager databaseManager = new MyDatabaseManager(this);

        // Supongamos que tienes un método en MyDatabaseManager para verificar las credenciales
        return databaseManager.checkCredentials(username, password);
    }

    // Método para guardar el nombre de usuario y la contraseña en las preferencias compartidas
    private void saveCredentialsInPreferences(String username, String password) {
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}
