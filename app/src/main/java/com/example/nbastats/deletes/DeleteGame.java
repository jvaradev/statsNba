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
    private Button deleteButton, btnBackToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_game);

        databaseManager = new DataBaseManager(this);
        gameIdEditText = findViewById(R.id.idGame);
        deleteButton = findViewById(R.id.btnDelete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGame();
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

    private void deleteGame() {
        String gameIdString = gameIdEditText.getText().toString();

        if (!gameIdString.isEmpty()) {
            try {
                databaseManager.open();
                int gameId = Integer.parseInt(gameIdString);
                int rowsAffected = databaseManager.deleteGameById(gameId);

                if (rowsAffected > 0) {
                    Toast.makeText(DeleteGame.this, R.string.gameDeleteOk, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteGame.this, R.string.gameDeleteNot, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(DeleteGame.this, R.string.gameDeleteFail, Toast.LENGTH_SHORT).show();
            } finally {
                databaseManager.close();
            }
        } else {
            Toast.makeText(DeleteGame.this, R.string.insertGameValidate, Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
