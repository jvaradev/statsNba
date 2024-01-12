package com.example.nbastats.updates;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

public class UpdateStats extends AppCompatActivity {

    private EditText idStatEditText, playerNameEditText, idGameEditText,
            plgEditText, ppgEditText, rlgEditText, rpgEditText,
            algEditText, apgEditText, slgEditText, blgEditText, llgEditText;

    private Button btnUpdateStat;

    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stats);

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();

        idStatEditText = findViewById(R.id.idStat);
        playerNameEditText = findViewById(R.id.playerName);
        idGameEditText = findViewById(R.id.idGame);
        plgEditText = findViewById(R.id.plg);
        ppgEditText = findViewById(R.id.ppg);
        rlgEditText = findViewById(R.id.rlg);
        rpgEditText = findViewById(R.id.rpg);
        algEditText = findViewById(R.id.alg);
        apgEditText = findViewById(R.id.apg);
        slgEditText = findViewById(R.id.slg);
        blgEditText = findViewById(R.id.blg);
        llgEditText = findViewById(R.id.llg);

        btnUpdateStat = findViewById(R.id.btnUpdateStat);
        btnUpdateStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStat();
            }
        });
    }

    private void updateStat() {
        int idStat = Integer.parseInt(idStatEditText.getText().toString());
        String playerName = playerNameEditText.getText().toString();
        int idGame = Integer.parseInt(idGameEditText.getText().toString());
        int plg = Integer.parseInt(plgEditText.getText().toString());
        double ppg = Double.parseDouble(ppgEditText.getText().toString());
        int rlg = Integer.parseInt(rlgEditText.getText().toString());
        double rpg = Double.parseDouble(rpgEditText.getText().toString());
        int alg = Integer.parseInt(algEditText.getText().toString());
        double apg = Double.parseDouble(apgEditText.getText().toString());
        int slg = Integer.parseInt(slgEditText.getText().toString());
        int blg = Integer.parseInt(blgEditText.getText().toString());
        int llg = Integer.parseInt(llgEditText.getText().toString());

        int rowsAffected = dataBaseManager.updateStat(idStat, getPlayerId(playerName), idGame, plg, ppg, rlg, rpg, alg, apg, slg, blg, llg);

        if (rowsAffected > 0) {
            Toast.makeText(this, "Estadística actualizada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar la estadística", Toast.LENGTH_SHORT).show();
        }
    }

    private int getPlayerId(String playerName) {
        int playerId = -1;
        Cursor cursor = dataBaseManager.getPlayersByField("player_name", playerName);
        if (cursor.moveToFirst()) {
            playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
        }
        cursor.close();
        return playerId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.close();
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
