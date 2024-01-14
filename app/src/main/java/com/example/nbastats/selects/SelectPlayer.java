package com.example.nbastats.selects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

import java.util.ArrayList;
import java.util.List;

public class SelectPlayer extends AppCompatActivity {

    private Spinner campoSpinner;
    private EditText claveEditText;
    private TextView textViewPlayerResults;
    private Button boton;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        campoSpinner = findViewById(R.id.edit);
        claveEditText = findViewById(R.id.clave);
        textViewPlayerResults = findViewById(R.id.textViewPlayerResults);
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
        List<String> camposList = getPlayerFields();

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

    private List<String> getPlayerFields() {
        List<String> camposList = new ArrayList<>();
        camposList.add("player_id");
        camposList.add("player_name");
        camposList.add("player_apel");
        camposList.add("player_position");
        camposList.add("player_country");
        camposList.add("team_id");
        return camposList;
    }

    @SuppressLint("Range")
    private void hacerSelect() {
        String campoSeleccionado = campoSpinner.getSelectedItem().toString();
        String clave = claveEditText.getText().toString();

        dataBaseManager.open();

        Cursor cursor = dataBaseManager.getPlayersByField(campoSeleccionado, clave);

        StringBuilder resultText = new StringBuilder();

        if (cursor.moveToFirst()) {
            do {
                int playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
                String playerName = cursor.getString(cursor.getColumnIndex("player_name"));
                String playerApel = cursor.getString(cursor.getColumnIndex("player_apel"));
                String position = cursor.getString(cursor.getColumnIndex("player_position"));
                String country = cursor.getString(cursor.getColumnIndex("player_country"));
                String team = cursor.getString(cursor.getColumnIndex("team_id"));

                resultText.append("Id jugador: ").append(playerId).append("\n");
                resultText.append("Nombre jugador: ").append(playerName).append("\n");
                resultText.append("Apellido jugador: ").append(playerApel).append("\n");
                resultText.append("Posición: ").append(position).append("\n");
                resultText.append("País: ").append(country).append("\n");
                resultText.append("Equipo: ").append(team).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            resultText.append("Resultados no encontrados");
        }

        textViewPlayerResults.setText(resultText.toString());

        dataBaseManager.close();
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
