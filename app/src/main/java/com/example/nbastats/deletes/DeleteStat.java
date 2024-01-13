package com.example.nbastats.deletes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastats.*;

public class DeleteStat extends AppCompatActivity {

    private EditText idStatEditText;
    private Button btnDelete;

    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_stat);

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();

        idStatEditText = findViewById(R.id.idStat);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStat();
            }
        });
    }

    private void deleteStat() {
        String idStatString = idStatEditText.getText().toString();

        if (idStatString.isEmpty()) {
            Toast.makeText(this, R.string.insertStatValidate, Toast.LENGTH_SHORT).show();
            return;
        }

        int idStat = Integer.parseInt(idStatString);

        int rowsAffected = dataBaseManager.deleteStatById(idStat);

        if (rowsAffected > 0) {
            Toast.makeText(this, R.string.statDeleteOk, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.statDeleteNot, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.close();
    }
    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
