package com.example.calculatusemestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviarMateria(View view){

        Intent primerCambio = new Intent(this, ActivityNotas.class);

        startActivity(primerCambio);

    }

    public void enviarSemestre(View view){

        Intent segundoCambio = new Intent(this, ActivitySemestre.class);

        startActivity(segundoCambio);

    }

}
