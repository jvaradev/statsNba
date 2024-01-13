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

public class InsertGame extends AppCompatActivity {

    private DataBaseManager databaseManager;
    private EditText fechaEditText, localEditText, puntosLocalEditText, visitanteEditText, puntosVisitanteEditText, mvpEditText;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_game);

        databaseManager = new DataBaseManager(this);

        fechaEditText = findViewById(R.id.fecha);
        localEditText = findViewById(R.id.local);
        puntosLocalEditText = findViewById(R.id.puntosLocal);
        visitanteEditText = findViewById(R.id.visitante);
        puntosVisitanteEditText = findViewById(R.id.puntosVisitante);
        mvpEditText = findViewById(R.id.mvp);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInsertButtonClick(view);
            }
        });
    }

    public void onInsertButtonClick(View view) {
        String fecha = fechaEditText.getText().toString();
        String local = localEditText.getText().toString();
        int puntosLocal = Integer.parseInt(puntosLocalEditText.getText().toString());
        String visitante = visitanteEditText.getText().toString();
        int puntosVisitante = Integer.parseInt(puntosVisitanteEditText.getText().toString());
        String mvp = mvpEditText.getText().toString();

        databaseManager.open();

        long result = databaseManager.insertGame(fecha, puntosLocal, puntosVisitante, local, visitante, mvp);

        databaseManager.close();

        if (result != -1) {
            Toast.makeText(InsertGame.this, R.string.insertGameOk, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InsertGame.this, R.string.insertGameNot, Toast.LENGTH_SHORT).show();
        }
    }
    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }

}
