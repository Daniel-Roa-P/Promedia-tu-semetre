package com.example.calculatusemestre;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityNotas extends AppCompatActivity {

    private EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_layout);

        cantidad = (EditText) findViewById(R.id.editText);

    }

    public void getNota(View view){

        Intent primerCambio = new Intent(this, SecondActivity.class);

        primerCambio.putExtra("cantidad",cantidad.getText().toString());
        startActivity(primerCambio);

    }

}
