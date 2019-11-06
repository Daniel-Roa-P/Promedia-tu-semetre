package com.example.calculatusemestre;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.calculatusemestre.SecondActivity.lista;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor;
    private double notaFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercer_layout);

        valor = (TextView) findViewById(R.id.notaFinal);

        for(int i=0;i<lista.size();i++){

            notaFinal =  notaFinal + (lista.get(i).getNota()*lista.get(i).getPorcentaje())/100.0;

        }

        valor.setText("Tu promedio es: "+ notaFinal);

    }

}
