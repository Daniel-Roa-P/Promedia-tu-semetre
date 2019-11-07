package com.example.calculatusemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ItemListAdapter adapter;
    private ListView listaNotas;
    private int cuadros,llenos,rango,totalPorcentajes;
    public static ArrayList<Contenedor>lista=new ArrayList<Contenedor>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo_layout);

        listaNotas = null;
        listaNotas =(ListView) findViewById(R.id.lista);


        cuadros = Integer.parseInt(getIntent().getStringExtra("cantidad"));

        for (int i = 0; i < cuadros; i++) {
            Contenedor contenedor=new Contenedor();

            lista.add(contenedor);
        }

        adapter = new ItemListAdapter(SecondActivity.this, R.layout.espacios,lista);
        listaNotas.setAdapter(adapter);

    }

    public void cambiar(View view) {

        for (int i = 0; i < lista.size(); i++) {

            if (!lista.get(i).getNota().equals("") && !lista.get(i).getPorcentaje().equals("")) {

                if (Integer.parseInt(lista.get(i).getNota()) >= 0 && Integer.parseInt(lista.get(i).getNota()) <= 50) {

                    rango++;

                }

                llenos++;
                totalPorcentajes = totalPorcentajes + Integer.parseInt(lista.get(i).getPorcentaje());
            }

        }

        if (!(llenos == cuadros)) {

            Toast.makeText(SecondActivity.this,
                    "Por favor llene todos los campos de texto", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else if(!(totalPorcentajes == 100)) {

            Toast.makeText(SecondActivity.this,
                    "la suma de los porcentajes debe ser 100", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else if(!(rango==cuadros)) {

            Toast.makeText(SecondActivity.this,
                    "ingrese las notas en el rango indicado", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else {

            llenos=0;
            totalPorcentajes=0;
            rango=0;

            Intent cambio = new Intent(this, ThirdActivity.class);
            startActivity(cambio);
        }



    }

}
