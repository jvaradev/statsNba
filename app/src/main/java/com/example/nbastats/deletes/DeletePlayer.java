package com.example.nbastats.deletes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.DataBaseManager;
import com.example.nbastats.Inicio;
import com.example.nbastats.R;

public class DeletePlayer extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText playerIdEditText;
    private Button btnDeletePlayer, btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_player);

        databaseManager = new DataBaseManager(this);

        playerIdEditText = findViewById(R.id.idPlayer);

        btnDeletePlayer = findViewById(R.id.btnDelete);
        btnDeletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeletePlayerButtonClick(view);
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

    public void onDeletePlayerButtonClick(View view) {
        String playerIdStr = playerIdEditText.getText().toString();

        if (!playerIdStr.isEmpty()) {
            int playerId = Integer.parseInt(playerIdStr);

            // Abrir la base de datos
            databaseManager.open();

            // Eliminar jugador
            int result = databaseManager.deletePlayerById(playerId);

            // Cerrar la base de datos
            databaseManager.close();

            if (result > 0) {
                // Éxito al eliminar
                Toast.makeText(this, "Jugador eliminado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                // Fallo al eliminar
                Toast.makeText(this, "Error al eliminar el jugador", Toast.LENGTH_SHORT).show();
            }
        } else {
            // El campo del ID del jugador está vacío
            Toast.makeText(this, "Ingrese el ID del jugador a eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
