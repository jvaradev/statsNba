package com.example.nbastats.selects;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.R;

// En tu actividad o fragmento
public class SelectStats extends AppCompatActivity {

    private TextView textViewGameResults;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stats);

        textViewGameResults = findViewById(R.id.textViewGameResults);

        // Inicializar el DataBaseManager
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();

        // Obtener el Cursor de la tabla "stat"
        Cursor cursor = dataBaseManager.getStats();

        // Mostrar los resultados en el TextView
        showStatsResults(cursor);
    }

    private void showStatsResults(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder stringBuilder = new StringBuilder();

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

                // Agregar los datos al StringBuilder
                stringBuilder.append("Stat ID: ").append(statId).append("\n");
                stringBuilder.append("Player ID: ").append(playerId).append("\n");
                stringBuilder.append("Game ID: ").append(gameId).append("\n");
                stringBuilder.append("Points Last Game: ").append(pointLastGame).append("\n");
                stringBuilder.append("Points Per Game: ").append(pointPerGame).append("\n");
                stringBuilder.append("Rebounds Last Game: ").append(reboundLastGame).append("\n");
                stringBuilder.append("Rebounds Per Game: ").append(reboundPerGame).append("\n");
                stringBuilder.append("Assists Last Game: ").append(assistsLastGame).append("\n");
                stringBuilder.append("Assists Per Game: ").append(assistsPerGame).append("\n");
                stringBuilder.append("Steals Last Game: ").append(stealsLastGame).append("\n");
                stringBuilder.append("Blocks Last Game: ").append(blocksLastGame).append("\n");
                stringBuilder.append("Lost Last Game: ").append(lostLastGame).append("\n\n");

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
