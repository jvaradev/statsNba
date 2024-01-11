package com.example.nbastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nbastats.inserts.InsertGame;
import com.example.nbastats.inserts.InsertPlayer;

public class Insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void irInsertGame (View view){
        Intent i = new Intent(this, InsertGame.class);
        startActivity(i);
    }
    public void irInsertPlayer (View view){
        Intent i = new Intent(this, InsertPlayer.class);
        startActivity(i);
    }
    /*public void irInsertStats (View view){
        Intent i = new Intent(this, InsertStats.class);
        startActivity(i);
    }
    public void irInsertTeam (View view){
        Intent i = new Intent(this, InsertTeam.class);
        startActivity(i);
    }*/

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}