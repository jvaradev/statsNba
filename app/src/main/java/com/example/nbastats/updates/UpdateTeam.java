package com.example.nbastats.updates;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

public class UpdateTeam extends AppCompatActivity {

    private DataBaseManager dbManager;
    private EditText idTeam, teamName, teamCity, teamArena, teamConference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);

        dbManager = new DataBaseManager(this);
        dbManager.open();

        idTeam = findViewById(R.id.idTeam);
        teamName = findViewById(R.id.teamName);
        teamCity = findViewById(R.id.teamCity);
        teamArena = findViewById(R.id.teamArena);
        teamConference = findViewById(R.id.teamConference);

        Button updateButton = findViewById(R.id.btnUpdateTeam);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTeam();
            }
        });

        Button backButton = findViewById(R.id.btnBackToMain);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateTeam() {
        String teamId = idTeam.getText().toString();
        String newName = teamName.getText().toString();
        String newCity = teamCity.getText().toString();
        String newArena = teamArena.getText().toString();
        String newConference = teamConference.getText().toString();

        if (!teamId.isEmpty()) {
            Cursor cursor = dbManager.getTeamsByField("team_id", teamId);

            if (cursor.moveToFirst()) {
                // Team exists, perform the update
                int teamIdToUpdate = cursor.getInt(cursor.getColumnIndex("team_id"));

                ContentValues values = new ContentValues();
                values.put("team_name", newName);
                values.put("team_city", newCity);
                values.put("team_arena", newArena);
                values.put("team_conference", newConference);

                int rowsAffected = dbManager.updateTeam(teamIdToUpdate, values);

                if (rowsAffected > 0) {
                    Toast.makeText(this, "Equipo actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al actualizar el equipo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No se encontró un equipo con el ID proporcionado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } else {
            Toast.makeText(this, "Ingrese un ID de equipo válido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
