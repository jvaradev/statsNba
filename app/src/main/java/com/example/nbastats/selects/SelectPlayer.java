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

        // Configurar el Spinner con los campos de la tabla Player
        configureSpinner();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerSelect();
            }
        });
    }

    private void configureSpinner() {
        // Obtener la lista de campos de la tabla Player
        List<String> camposList = getPlayerFields();

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

    private List<String> getPlayerFields() {
        // Puedes personalizar esta lista según los campos que tengas en tu tabla Player
        List<String> camposList = new ArrayList<>();
        camposList.add("player_id");
        camposList.add("player_name");
        camposList.add("player_apel");
        camposList.add("player_position");
        camposList.add("player_country");
        camposList.add("team_id");
        return camposList;
    }

    private void hacerSelect() {
        String campoSeleccionado = campoSpinner.getSelectedItem().toString();
        String clave = claveEditText.getText().toString();

        dataBaseManager.open();

        // Realizar la consulta a la tabla Player
        Cursor cursor = dataBaseManager.getPlayersByField(campoSeleccionado, clave);

        StringBuilder resultText = new StringBuilder();

        // Iterar sobre el cursor y obtener los resultados
        if (cursor.moveToFirst()) {
            do {
                int playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
                String playerName = cursor.getString(cursor.getColumnIndex("player_name"));
                String playerApel = cursor.getString(cursor.getColumnIndex("player_apel"));
                String position = cursor.getString(cursor.getColumnIndex("player_position"));
                String country = cursor.getString(cursor.getColumnIndex("player_country"));
                String team = cursor.getString(cursor.getColumnIndex("team_id"));

                resultText.append("Player ID: ").append(playerId).append("\n");
                resultText.append("Nombre: ").append(playerName).append("\n");
                resultText.append("Apellido: ").append(playerApel).append("\n");
                resultText.append("Posición: ").append(position).append("\n");
                resultText.append("País: ").append(country).append("\n");
                resultText.append("Equipo: ").append(team).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            resultText.append("No se encontraron resultados.");
        }

        textViewPlayerResults.setText(resultText.toString());

        dataBaseManager.close();
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
