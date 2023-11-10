package com.example.proyecto_bitacores;

import static com.example.proyecto_bitacores.DatabaseHelper.COLUMN_PASSWORD;
import static com.example.proyecto_bitacores.DatabaseHelper.COLUMN_USERNAME;
import static com.example.proyecto_bitacores.DatabaseHelper.TABLE_USERS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseManager extends SQLiteOpenHelper {

    // Constructor
    public MyDatabaseManager(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí deberías crear tus tablas de base de datos si no existen
        db.execSQL(DatabaseHelper.CREATE_TABLE_USERS);
        db.execSQL(DatabaseHelper.CREATE_TABLE_PRODUCTS);

        // Agrega más sentencias CREATE TABLE según sea necesario
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes manejar actualizaciones de la base de datos, si es necesario
    }

    // Método para insertar un usuario
    public long insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        return getWritableDatabase().insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    // Método para insertar un producto
    public long insertProduct(String productName, double productPrice, String productImage) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUCT_NAME, productName);
        values.put(DatabaseHelper.COLUMN_PRODUCT_PRICE, productPrice);
        values.put(DatabaseHelper.COLUMN_PRODUCT_IMAGE, productImage);
        return getWritableDatabase().insert(DatabaseHelper.TABLE_PRODUCTS, null, values);
    }



    // Método para verificar las credenciales
    public boolean checkCredentials(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        try {
            // Consulta para verificar las credenciales en la tabla de usuarios
            String query = "SELECT * FROM " + DatabaseHelper.TABLE_USERS +
                    " WHERE " + DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
            cursor = db.rawQuery(query, new String[]{username, password});

            // Verifica si hay al menos un resultado en el cursor
            return cursor.moveToFirst();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Consulta para obtener todos los productos
            String query = "SELECT * FROM " + DatabaseHelper.TABLE_PRODUCTS;
            cursor = db.rawQuery(query, null);

            // Recorrer el cursor y agregar productos a la lista
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int productId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_PRICE));
                @SuppressLint("Range") int productImage = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_IMAGE));

                // Crear un objeto Product y agregarlo a la lista
                Product product = new Product(productName, productImage, productPrice);
                productList.add(product);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return productList;
    }


    // Otros métodos según sea necesario, como consultar usuarios, productos, etc.
}
