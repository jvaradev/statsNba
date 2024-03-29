package com.example.nbastats.deletes;

import android.content.Intent;
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

public class DeleteTeam extends AppCompatActivity {

    private DataBaseManager dbManager;

    private EditText teamIdEditText;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_team);

        dbManager = new DataBaseManager(this);
        dbManager.open();

        teamIdEditText = findViewById(R.id.idTeam);

        deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClick();
            }
        });
    }

    private void onDeleteButtonClick() {
        // Retrieve the team ID from the EditText
        String teamId = teamIdEditText.getText().toString().trim();

        if (!teamId.isEmpty()) {
            int rowsAffected = dbManager.deleteTeamByName(teamId);

            if (rowsAffected > 0) {
                Toast.makeText(this, R.string.teamDeleteOk, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.teamDeleteNot, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.insertTeamValidate, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
    public void irInicio(View view) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}
