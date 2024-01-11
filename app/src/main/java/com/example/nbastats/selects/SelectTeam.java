package com.example.nbastats.selects;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.R;

public class SelectTeam extends AppCompatActivity {

    private TextView textViewGameResults;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        textViewGameResults = findViewById(R.id.textViewGameResults);

        // Inicializar el DataBaseManager
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();

        // Obtener el Cursor de la tabla "team"
        Cursor cursor = dataBaseManager.getTeams();

        // Mostrar los resultados en el TextView
        showTeamsResults(cursor);
    }

    private void showTeamsResults(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder stringBuilder = new StringBuilder();

            do {
                int teamId = cursor.getInt(cursor.getColumnIndex("team_id"));
                String teamName = cursor.getString(cursor.getColumnIndex("team_name"));
                String teamCity = cursor.getString(cursor.getColumnIndex("team_city"));
                String teamArena = cursor.getString(cursor.getColumnIndex("team_arena"));
                String teamConference = cursor.getString(cursor.getColumnIndex("team_conference"));
                int homeId = cursor.getInt(cursor.getColumnIndex("home_id"));
                int awayId = cursor.getInt(cursor.getColumnIndex("away_id"));

                // Agregar los datos al StringBuilder
                stringBuilder.append("Team ID: ").append(teamId).append("\n");
                stringBuilder.append("Team Name: ").append(teamName).append("\n");
                stringBuilder.append("Team City: ").append(teamCity).append("\n");
                stringBuilder.append("Team Arena: ").append(teamArena).append("\n");
                stringBuilder.append("Team Conference: ").append(teamConference).append("\n");
                stringBuilder.append("Home ID: ").append(homeId).append("\n");
                stringBuilder.append("Away ID: ").append(awayId).append("\n\n");

            } while (cursor.moveToNext());

            // Mostrar los resultados en el TextView
            textViewGameResults.setText(stringBuilder.toString());

            // Cerrar el cursor
            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cerrar la conexión con la base de datos al destruir la actividad
        dataBaseManager.close();
    }
}
