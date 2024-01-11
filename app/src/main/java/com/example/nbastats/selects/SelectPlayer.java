package com.example.nbastats.selects;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.R;

public class SelectPlayer extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private TextView textViewPlayersResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        databaseManager = new DataBaseManager(this);

        // Inicializar el TextView según su ID en el diseño XML
        textViewPlayersResults = findViewById(R.id.textViewPlayerResults);

        // Abrir la base de datos
        databaseManager.open();

        // Obtener un cursor con los resultados de la consulta a la tabla "player"
        Cursor cursor = databaseManager.getPlayers();

        // Mostrar los resultados en el TextView
        displayPlayers(cursor);

        // Cerrar la base de datos
        databaseManager.close();
    }

    private void displayPlayers(Cursor cursor) {
        // Verificar si hay datos en el cursor
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder playerInfo = new StringBuilder();

            // Iterar a través de las filas del cursor
            do {
                // Obtener datos de la fila actual
                int playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
                String playerName = cursor.getString(cursor.getColumnIndex("player_name"));
                String playerApel = cursor.getString(cursor.getColumnIndex("player_apel"));
                String playerPosition = cursor.getString(cursor.getColumnIndex("player_position"));
                String playerCountry = cursor.getString(cursor.getColumnIndex("player_country"));
                int teamId = cursor.getInt(cursor.getColumnIndex("team_id"));

                // Construir la cadena con la información del jugador
                String playerDetails = "ID: " + playerId +
                        "\nName: " + playerName +
                        "\nApel: " + playerApel +
                        "\nPosition: " + playerPosition +
                        "\nCountry: " + playerCountry +
                        "\nTeam ID: " + teamId + "\n\n";

                // Agregar la información del jugador al StringBuilder
                playerInfo.append(playerDetails);

            } while (cursor.moveToNext());

            // Mostrar la información en el TextView
            textViewPlayersResults.setText(playerInfo.toString());
        } else {
            // Si no hay datos, mostrar un mensaje indicando que no hay jugadores
            textViewPlayersResults.setText("No hay jugadores disponibles.");
        }
    }
}
