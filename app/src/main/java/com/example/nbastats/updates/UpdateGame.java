package com.example.nbastats.updates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.Inicio;
import com.example.nbastats.R;

public class UpdateGame extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText editTextGameId;
    private EditText editTextGameDate;
    private EditText editTextHomeId;
    private EditText editTextAwayId;
    private EditText editTextHomePoints;
    private EditText editTextAwayPoints;
    private EditText editTextMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);

        databaseManager = new DataBaseManager(this);

        // Inicializar los EditText y otros elementos según sea necesario
        editTextGameId = findViewById(R.id.idGame);
        editTextGameDate = findViewById(R.id.fecha);
        editTextHomeId = findViewById(R.id.local);
        editTextAwayId = findViewById(R.id.visitante);
        editTextHomePoints = findViewById(R.id.puntosLocal);
        editTextAwayPoints = findViewById(R.id.puntosVisitante);
        editTextMvp = findViewById(R.id.mvp);
    }

    public void onUpdateButtonClick(View view) {
        // Obtener los valores de los EditText
        int gameId = Integer.parseInt(editTextGameId.getText().toString());
        String gameDate = editTextGameDate.getText().toString();
        String homeId = editTextHomeId.getText().toString();
        String awayId = editTextAwayId.getText().toString();
        int homePoints = Integer.parseInt(editTextHomePoints.getText().toString());
        int awayPoints = Integer.parseInt(editTextAwayPoints.getText().toString());
        String mvp = editTextMvp.getText().toString();

        // Abrir la base de datos
        databaseManager.open();

        // Actualizar el juego en la base de datos
        int rowsAffected = databaseManager.updateGame(gameId, gameDate, homePoints, awayPoints, homeId, awayId, mvp);

        // Cerrar la base de datos
        databaseManager.close();

        // Verificar si la actualización fue exitosa
        if (rowsAffected > 0) {
            Toast.makeText(this, "Juego actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar el juego", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
