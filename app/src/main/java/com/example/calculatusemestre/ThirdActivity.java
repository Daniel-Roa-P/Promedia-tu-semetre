package com.example.calculatusemestre;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.calculatusemestre.SecondActivity.lista;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor;
    private int notaFinal;

    private String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercer_layout);

        valor = (TextView) findViewById(R.id.notaFinal);

        for(int i=0;i<lista.size();i++){

            cadena = cadena + Integer.toString(lista.get(i).getNota()) + " - "+ Integer.toString(lista.get(i).getPorcentaje());

        }

        valor.setText(cadena);

    }

}
