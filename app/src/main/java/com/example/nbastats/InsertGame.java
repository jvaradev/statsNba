package com.example.nbastats;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

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
        // Obtener datos del formulario
        String fecha = fechaEditText.getText().toString();
        String local = localEditText.getText().toString();
        int puntosLocal = Integer.parseInt(puntosLocalEditText.getText().toString());
        String visitante = visitanteEditText.getText().toString();
        int puntosVisitante = Integer.parseInt(puntosVisitanteEditText.getText().toString());
        String mvp = mvpEditText.getText().toString();

        // Abrir la base de datos
        databaseManager.open();

        // Insertar juego en la base de datos
        long result = databaseManager.insertGame(fecha, puntosLocal, puntosVisitante, 1, 2, 3);

        // Cerrar la base de datos
        databaseManager.close();

        if (result != -1) {
            // Éxito al insertar
            // Puedes realizar acciones adicionales o mostrar un mensaje de éxito
        } else {
            // Falla al insertar
            // Puedes manejar la falla de alguna manera (mostrar un mensaje de error, etc.)
        }
    }
}
