// InsertTeam.java
package com.example.nbastats.inserts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.Inicio;
import com.example.nbastats.R;

public class InsertTeam extends AppCompatActivity {

    private EditText teamNameEditText, teamCityEditText, teamArenaEditText, teamConferenceEditText;
    private Button insertButton;
    private DataBaseManager dataBaseManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_team);

        teamNameEditText = findViewById(R.id.teamName);
        teamCityEditText = findViewById(R.id.teamCity);
        teamArenaEditText = findViewById(R.id.teamArena);
        teamConferenceEditText = findViewById(R.id.teamConference);

        insertButton = findViewById(R.id.btnInsertPlayer);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTeam();
            }
        });

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();
    }

    private void insertTeam() {
        String teamName = teamNameEditText.getText().toString();
        String teamCity = teamCityEditText.getText().toString();
        String teamArena = teamArenaEditText.getText().toString();
        String teamConference = teamConferenceEditText.getText().toString();

        long result = dataBaseManager.insertTeam(teamName, teamCity, teamArena, teamConference);

        if (result != -1) {
            Toast.makeText(this, "Equipo insertado correctamente", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Error al insertar el equipo", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        teamNameEditText.setText("");
        teamCityEditText.setText("");
        teamArenaEditText.setText("");
        teamConferenceEditText.setText("");
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
