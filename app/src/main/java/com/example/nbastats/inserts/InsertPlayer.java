package com.example.nbastats.inserts;

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
import com.example.nbastats.deletes.DeleteGame;

public class InsertPlayer extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText playerNameEditText, playerApelEditText, playerPositionEditText, playerCountryEditText, playerTeamEditText;
    private Button btnInsertPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_player);

        databaseManager = new DataBaseManager(this);

        playerNameEditText = findViewById(R.id.playerName);
        playerApelEditText = findViewById(R.id.playerApel);
        playerPositionEditText = findViewById(R.id.playerPosition);
        playerCountryEditText = findViewById(R.id.playerCountry);
        playerTeamEditText = findViewById(R.id.idTeam);

        btnInsertPlayer = findViewById(R.id.btnInsertPlayer);
        btnInsertPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInsertPlayerButtonClick(view);
            }
        });
    }

    public void onInsertPlayerButtonClick(View view) {
        // Obtener datos del formulario
        String playerName = playerNameEditText.getText().toString();
        String playerApel = playerApelEditText.getText().toString();
        String playerPosition = playerPositionEditText.getText().toString();
        String playerCountry = playerCountryEditText.getText().toString();
        String teamId = playerTeamEditText.getText().toString();

        // Abrir la base de datos
        databaseManager.open();

        // Insertar jugador en la base de datos
        long result = databaseManager.insertPlayer(playerName, playerApel, playerPosition, playerCountry, teamId);

        // Cerrar la base de datos
        databaseManager.close();

        if (result != -1) {
            Toast.makeText(InsertPlayer.this, "Jugador insertado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InsertPlayer.this, "Fallo inserta jugador", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
