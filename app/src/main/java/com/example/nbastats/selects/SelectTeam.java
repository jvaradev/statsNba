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

public class SelectTeam extends AppCompatActivity {

    private Spinner campoSpinner;
    private EditText claveEditText;
    private TextView textViewTeamResults;
    private Button boton;
    private DataBaseManager dataBaseManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        campoSpinner = findViewById(R.id.edit);
        claveEditText = findViewById(R.id.clave);
        textViewTeamResults = findViewById(R.id.textViewTeamResults);
        boton = findViewById(R.id.boton);
        dataBaseManager = new DataBaseManager(this);

        // Configurar el Spinner con los campos de la tabla Team
        configureSpinner();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerSelect();
            }
        });
    }

    private void configureSpinner() {
        // Obtener la lista de campos de la tabla Team
        List<String> camposList = getTeamFields();

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

    private List<String> getTeamFields() {
        // Puedes personalizar esta lista según los campos que tengas en tu tabla Team
        List<String> camposList = new ArrayList<>();
        camposList.add("team_id");
        camposList.add("team_name");
        camposList.add("team_city");
        camposList.add("team_arena");
        camposList.add("team_conference");
        return camposList;
    }

    private void hacerSelect() {
        String campoSeleccionado = campoSpinner.getSelectedItem().toString();
        String clave = claveEditText.getText().toString();

        dataBaseManager.open();

        // Realizar la consulta a la tabla Team
        Cursor cursor = dataBaseManager.getTeamsByField(campoSeleccionado, clave);

        StringBuilder resultText = new StringBuilder();

        // Iterar sobre el cursor y obtener los resultados
        if (cursor.moveToFirst()) {
            do {
                int teamId = cursor.getInt(cursor.getColumnIndex("team_id"));
                String teamName = cursor.getString(cursor.getColumnIndex("team_name"));
                String teamCity = cursor.getString(cursor.getColumnIndex("team_city"));
                String teamArena = cursor.getString(cursor.getColumnIndex("team_arena"));
                String teamConference = cursor.getString(cursor.getColumnIndex("team_conference"));

                resultText.append("Team ID: ").append(teamId).append("\n");
                resultText.append("Nombre del equipo: ").append(teamName).append("\n");
                resultText.append("Ciudad: ").append(teamCity).append("\n");
                resultText.append("Arena: ").append(teamArena).append("\n");
                resultText.append("Conferencia: ").append(teamConference).append("\n");
            } while (cursor.moveToNext());
        } else {
            resultText.append("No se encontraron resultados.");
        }

        textViewTeamResults.setText(resultText.toString());

        dataBaseManager.close();
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
