package com.example.nbastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void irSelect (View view){
        Intent i = new Intent(this, Select.class);
        startActivity(i);
    }
    public void irInsert (View view){
        Intent i = new Intent(this, Insert.class);
        startActivity(i);
    }
    public void irUpdate (View view){
        Intent i = new Intent(this, Update.class);
        startActivity(i);
    }
    public void irDelete (View view){
        Intent i = new Intent(this, Delete.class);
        startActivity(i);
    }

    public void irMain (View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}