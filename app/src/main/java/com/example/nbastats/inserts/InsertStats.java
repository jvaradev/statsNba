package com.example.nbastats.inserts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

public class InsertStats extends AppCompatActivity {

    private EditText playerNameEditText, idGameEditText, plgEditText, ppgEditText, rlgEditText, rpgEditText,
            algEditText, apgEditText, slgEditText, blgEditText, llgEditText;
    private DataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_stats);

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

        Button insertButton = findViewById(R.id.btnInsertPStats);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStats();
            }
        });

        Button backButton = findViewById(R.id.btnBackToMain);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irInicio();
            }
        });

        db = new DataBaseManager(this);
        db.open();
    }

    private void irInicio() {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }

    private void insertStats() {
        String playerName = playerNameEditText.getText().toString();
        String idGame = idGameEditText.getText().toString();
        String plg = plgEditText.getText().toString();
        String ppg = ppgEditText.getText().toString();
        String rlg = rlgEditText.getText().toString();
        String rpg = rpgEditText.getText().toString();
        String alg = algEditText.getText().toString();
        String apg = apgEditText.getText().toString();
        String slg = slgEditText.getText().toString();
        String blg = blgEditText.getText().toString();
        String llg = llgEditText.getText().toString();

        if (TextUtils.isEmpty(playerName) || TextUtils.isEmpty(idGame) || TextUtils.isEmpty(plg) ||
                TextUtils.isEmpty(ppg) || TextUtils.isEmpty(rlg) || TextUtils.isEmpty(rpg) || TextUtils.isEmpty(alg) ||
                TextUtils.isEmpty(apg) || TextUtils.isEmpty(slg) || TextUtils.isEmpty(blg) || TextUtils.isEmpty(llg)) {
            Toast.makeText(this, R.string.valueEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        int playerId = db.getPlayerIdByName(playerName);
        int gameId = Integer.parseInt(idGame);

        Cursor existingStats = db.getStatsByPlayerAndGame(playerId, gameId);
        if (existingStats != null && existingStats.getCount() > 0) {
            Toast.makeText(this, R.string.existStat, Toast.LENGTH_SHORT).show();
            existingStats.close();
            return;
        }

        int plgValue = Integer.parseInt(plg);
        double ppgValue = Double.parseDouble(ppg);
        int rlgValue = Integer.parseInt(rlg);
        double rpgValue = Double.parseDouble(rpg);
        int algValue = Integer.parseInt(alg);
        double apgValue = Double.parseDouble(apg);
        int slgValue = Integer.parseInt(slg);
        int blgValue = Integer.parseInt(blg);
        int llgValue = Integer.parseInt(llg);

        long result = db.insertStat(playerId, gameId, plgValue, ppgValue, rlgValue, rpgValue, algValue, apgValue, slgValue, blgValue, llgValue);

        if (result != -1) {
            Toast.makeText(this, R.string.insertStatOk, Toast.LENGTH_SHORT).show();
            // Resto de la lógica, si es necesario
        } else {
            Toast.makeText(this, R.string.insertStatNot, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

}
