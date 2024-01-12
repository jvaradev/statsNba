package com.example.nbastats.selects;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

import java.util.*;

public class SelectGame extends AppCompatActivity {

    private Spinner campoSpinner;
    private EditText claveEditText;
    private TextView textViewGameResults;
    private Button boton;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        campoSpinner = findViewById(R.id.edit);
        claveEditText = findViewById(R.id.clave);
        textViewGameResults = findViewById(R.id.textViewGameResults);
        boton = findViewById(R.id.boton);
        dataBaseManager = new DataBaseManager(this);

        // Configurar el Spinner con los campos de la tabla Game
        configureSpinner();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerSelect();
            }
        });
    }

    private void configureSpinner() {
        // Obtener la lista de campos de la tabla Game
        List<String> camposList = getGameFields();

        // Crear un adaptador para el Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, camposList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Configurar el adaptador en el Spinner
        campoSpinner.setAdapter(spinnerAdapter);

        // Manejar eventos de selección en el Spinner
        campoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Al seleccionar un campo, realizar la consulta
                hacerSelect();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No es necesario manejar este evento en este caso
            }
        });
    }

    private List<String> getGameFields() {
        // Puedes personalizar esta lista según los campos que tengas en tu tabla Game
        List<String> camposList = new ArrayList<>();
        camposList.add("game_id");
        camposList.add("game_date");
        camposList.add("home_points");
        camposList.add("away_points");
        camposList.add("home_id");
        camposList.add("away_id");
        camposList.add("mvp");
        return camposList;
    }

    private void hacerSelect() {
        String campoSeleccionado = campoSpinner.getSelectedItem().toString();
        String clave = claveEditText.getText().toString();

        dataBaseManager.open();

        // Realizar la consulta a la tabla Game
        Cursor cursor = dataBaseManager.getGamesByField(campoSeleccionado, clave);

        StringBuilder resultText = new StringBuilder();

        // Iterar sobre el cursor y obtener los resultados
        if (cursor.moveToFirst()) {
            do {
                int gameId = cursor.getInt(cursor.getColumnIndex("game_id"));
                String gameDate = cursor.getString(cursor.getColumnIndex("game_date"));
                int homePoints = cursor.getInt(cursor.getColumnIndex("home_points"));
                int awayPoints = cursor.getInt(cursor.getColumnIndex("away_points"));
                String homeId = cursor.getString(cursor.getColumnIndex("home_id"));
                String awayId = cursor.getString(cursor.getColumnIndex("away_id"));
                String mvp = cursor.getString(cursor.getColumnIndex("mvp"));

                resultText.append("Game ID: ").append(gameId).append("\n");
                resultText.append("Game Date: ").append(gameDate).append("\n");
                resultText.append("Home Points: ").append(homePoints).append("\n");
                resultText.append("Away Points: ").append(awayPoints).append("\n");
                resultText.append("Home ID: ").append(homeId).append("\n");
                resultText.append("Away ID: ").append(awayId).append("\n");
                resultText.append("MVP: ").append(mvp).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            resultText.append("No se encontraron resultados.");
        }

        textViewGameResults.setText(resultText.toString());

        dataBaseManager.close();
    }
    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
