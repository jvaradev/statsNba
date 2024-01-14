package com.example.nbastats.selects;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

import java.util.ArrayList;
import java.util.List;

public class SelectStats extends AppCompatActivity {

    private Spinner campoSpinner;
    private EditText claveEditText;
    private TextView textViewStatsResults;
    private Button boton;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stats);

        campoSpinner = findViewById(R.id.edit);
        claveEditText = findViewById(R.id.clave);
        textViewStatsResults = findViewById(R.id.textViewStatsResults);
        boton = findViewById(R.id.boton);
        dataBaseManager = new DataBaseManager(this);

        configureSpinner();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerSelect();
            }
        });
    }

    private void configureSpinner() {
        List<String> camposList = getStatsFields();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, camposList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        campoSpinner.setAdapter(spinnerAdapter);

        campoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                hacerSelect();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private List<String> getStatsFields() {
        List<String> camposList = new ArrayList<>();
        camposList.add("stat_id");
        camposList.add("player_id");
        camposList.add("game_id");
        camposList.add("pointlastg");
        camposList.add("pointperg");
        camposList.add("reboundlasg");
        camposList.add("reboundperg");
        camposList.add("assitslastg");
        camposList.add("assitsperg");
        camposList.add("steallastg");
        camposList.add("blocklastg");
        camposList.add("lostlastg");
        return camposList;
    }

    private void hacerSelect() {
        String campoSeleccionado = campoSpinner.getSelectedItem().toString();
        String clave = claveEditText.getText().toString();

        dataBaseManager.open();

        Cursor cursor = dataBaseManager.getStatsByField(campoSeleccionado, clave);

        StringBuilder resultText = new StringBuilder();

        if (cursor.moveToFirst()) {
            do {
                int statId = cursor.getInt(cursor.getColumnIndex("stat_id"));
                int playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
                int gameId = cursor.getInt(cursor.getColumnIndex("game_id"));
                int pointLastGame = cursor.getInt(cursor.getColumnIndex("pointlastg"));
                double pointPerGame = cursor.getDouble(cursor.getColumnIndex("pointperg"));
                int reboundLastGame = cursor.getInt(cursor.getColumnIndex("reboundlasg"));
                double reboundPerGame = cursor.getDouble(cursor.getColumnIndex("reboundperg"));
                int assistsLastGame = cursor.getInt(cursor.getColumnIndex("assitslastg"));
                double assistsPerGame = cursor.getDouble(cursor.getColumnIndex("assitsperg"));
                int stealsLastGame = cursor.getInt(cursor.getColumnIndex("steallastg"));
                int blocksLastGame = cursor.getInt(cursor.getColumnIndex("blocklastg"));
                int lostLastGame = cursor.getInt(cursor.getColumnIndex("lostlastg"));

                resultText.append(R.string.statId).append(statId).append("\n");
                resultText.append(R.string.playerId).append(playerId).append("\n");
                resultText.append(R.string.gameId).append(gameId).append("\n");
                resultText.append(R.string.plg).append(pointLastGame).append("\n");
                resultText.append(R.string.ppg).append(pointPerGame).append("\n");
                resultText.append(R.string.rlg).append(reboundLastGame).append("\n");
                resultText.append(R.string.rpg).append(reboundPerGame).append("\n");
                resultText.append(R.string.alg).append(assistsLastGame).append("\n");
                resultText.append(R.string.apg).append(assistsPerGame).append("\n");
                resultText.append(R.string.slg).append(stealsLastGame).append("\n");
                resultText.append(R.string.blg).append(blocksLastGame).append("\n");
                resultText.append(R.string.llg).append(lostLastGame).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            resultText.append("Resultados no encontrados");
        }

        textViewStatsResults.setText(resultText.toString());

        dataBaseManager.close();
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
