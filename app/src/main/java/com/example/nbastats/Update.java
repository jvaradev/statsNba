package com.example.nbastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nbastats.deletes.DeleteGame;
import com.example.nbastats.selects.SelectPlayer;
import com.example.nbastats.updates.UpdateGame;
import com.example.nbastats.updates.UpdatePlayer;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }
    public void irUpdateGame (View view){
        Intent i = new Intent(this, UpdateGame.class);
        startActivity(i);
    }
    public void irUpdatePlayer (View view){
        Intent i = new Intent(this, UpdatePlayer.class);
        startActivity(i);
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }

}