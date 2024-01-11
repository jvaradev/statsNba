package com.example.nbastats.deletes;

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

public class DeleteGame extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText gameIdEditText;
    private Button deleteButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_game);

        databaseManager = new DataBaseManager(this);
        gameIdEditText = findViewById(R.id.fecha); // Asumo que el ID de partido se ingresa en un EditText con id 'fecha'
        deleteButton = findViewById(R.id.btnDelete); // Asegúrate de que el botón tenga el ID correcto en tu diseño XML

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGame();
            }
        });
    }

    private void deleteGame() {
        // Obtener el ID del juego a eliminar
        String gameIdString = gameIdEditText.getText().toString();

        if (!gameIdString.isEmpty()) {
            try {
                databaseManager.open();

                // Convertir el ID del juego a entero
                int gameId = Integer.parseInt(gameIdString);

                // Eliminar el juego
                int rowsAffected = databaseManager.deleteGameById(gameId);

                if (rowsAffected > 0) {
                    Toast.makeText(DeleteGame.this, "Juego eliminado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteGame.this, "No se encontró el juego con el ID especificado", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(DeleteGame.this, "Error al eliminar el juego", Toast.LENGTH_SHORT).show();
            } finally {
                databaseManager.close();
            }
        } else {
            Toast.makeText(DeleteGame.this, "Ingrese un ID de juego válido", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
