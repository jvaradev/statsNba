package com.example.nbastats.deletes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nbastats.Inicio;
import com.example.nbastats.R;
import com.example.nbastats.deletes.DeleteGame;
import com.example.nbastats.deletes.DeletePlayer;
import com.example.nbastats.deletes.DeleteStat;
import com.example.nbastats.deletes.DeleteTeam;
import com.example.nbastats.selects.SelectGame;
import com.example.nbastats.selects.SelectPlayer;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }

    public void irDeleteGame (View view){
        Intent i = new Intent(this, DeleteGame.class);
        startActivity(i);
    }
    public void irDeletePlayer (View view){
        Intent i = new Intent(this, DeletePlayer.class);
        startActivity(i);
    }
    public void irDeleteTeam (View view){
        Intent i = new Intent(this, DeleteTeam.class);
        startActivity(i);
    }
    public void irDeleteStats (View view){
        Intent i = new Intent(this, DeleteStat.class);
        startActivity(i);
    }
    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}