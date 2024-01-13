package com.example.nbastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            dataBase = new DataBase(this);

            SQLiteDatabase db = dataBase.getWritableDatabase();

            Log.d("DATABASE", "Base de datos abierta correctamente.");
        } catch (Exception e) {
            Log.e("DATABASE", "Error al abrir la base de datos: " + e.getMessage());
        }

    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}