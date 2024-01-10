package com.example.nbastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class Select extends AppCompatActivity {

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //Cursor c = db.getReadableDatabase().rawQuery();
    }

    public void irSelectGame (View view){
        Intent i = new Intent(this, SelectGame.class);
        startActivity(i);
    }
}