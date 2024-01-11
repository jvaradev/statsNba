package com.example.nbastats.updates;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.Inicio;
import com.example.nbastats.R;

public class UpdatePlayer extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText playerIdEditText, playerNameEditText, playerApelEditText, playerPositionEditText, playerCountryEditText, idTeamEditText;
    private Button btnUpdatePlayer, btnBackToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        databaseManager = new DataBaseManager(this);

        playerIdEditText = findViewById(R.id.idPlayer);
        playerNameEditText = findViewById(R.id.playerName);
        playerApelEditText = findViewById(R.id.playerApel);
        playerPositionEditText = findViewById(R.id.playerPosition);
        playerCountryEditText = findViewById(R.id.playerCountry);
        idTeamEditText = findViewById(R.id.idTeam);

        btnUpdatePlayer = findViewById(R.id.btnUpdatePlayer);
        btnUpdatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdatePlayerButtonClick(view);
            }
        });

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irInicio(view);
            }
        });
    }

    public void onUpdatePlayerButtonClick(View view) {
        String playerIdStr = playerIdEditText.getText().toString();

        if (!playerIdStr.isEmpty()) {
            int playerId = Integer.parseInt(playerIdStr);

            // Obtener nuevos datos del formulario
            String playerName = playerNameEditText.getText().toString();
            String playerApel = playerApelEditText.getText().toString();
            String playerPosition = playerPositionEditText.getText().toString();
            String playerCountry = playerCountryEditText.getText().toString();
            String idTeam = idTeamEditText.getText().toString(); // Debes proporcionar el ID del equipo, puede ser fijo o dinámico según tu lógica

            // Abrir la base de datos
            databaseManager.open();

            // Actualizar jugador
            int result = databaseManager.updatePlayer(playerId, playerName, playerApel, playerPosition, playerCountry, idTeam);

            // Cerrar la base de datos
            databaseManager.close();

            if (result > 0) {
                // Éxito al actualizar
                Toast.makeText(this, "Jugador actualizado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                // Fallo al actualizar
                Toast.makeText(this, "Error al actualizar el jugador", Toast.LENGTH_SHORT).show();
            }
        } else {
            // El campo del ID del jugador está vacío
            Toast.makeText(this, "Ingrese el ID del jugador a actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
