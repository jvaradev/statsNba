package com.example.nbastats;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class SelectGame extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private TextView textViewGameResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        databaseManager = new DataBaseManager(this);
        textViewGameResults = findViewById(R.id.textViewGameResults);

        displayGames();
    }

    private void displayGames() {
        // Abrir la base de datos
        databaseManager.open();

        // Realizar la consulta de la tabla "Game"
        Cursor cursor = databaseManager.getGamesCursor();

        // Mostrar los resultados en el TextView
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder resultStringBuilder = new StringBuilder();

            do {
                String gameDate = cursor.getString(cursor.getColumnIndex("game_date"));
                int homePoints = cursor.getInt(cursor.getColumnIndex("home_points"));
                int awayPoints = cursor.getInt(cursor.getColumnIndex("away_points"));

                // Concatenar los resultados
                resultStringBuilder.append(String.format(Locale.getDefault(),
                        "Game Date: %s\nHome Points: %d\nAway Points: %d\n\n",
                        gameDate, homePoints, awayPoints));

            } while (cursor.moveToNext());

            // Establecer la cadena en el TextView
            textViewGameResults.setText(resultStringBuilder.toString());
        } else {
            textViewGameResults.setText("No games found.");
        }

        // Cerrar la base de datos
        databaseManager.close();
    }
}
