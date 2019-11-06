package com.example.calculatusemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ItemListAdapter adapter;
    private ListView listaNotas;
    private int cuadros;
    public static ArrayList<Contenedor>lista=new ArrayList<Contenedor>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo_layout);

        listaNotas =(ListView) findViewById(R.id.lista);


        cuadros = Integer.parseInt(getIntent().getStringExtra("cantidad"));

        for (int i = 0; i < cuadros; i++) {
            Contenedor contenedor=new Contenedor();

            lista.add(contenedor);
        }

        adapter = new ItemListAdapter(SecondActivity.this, R.layout.espacios,lista);
        listaNotas.setAdapter(adapter);

    }

    public void cambiar(View view){

        Intent cambio = new Intent(this,ThirdActivity.class);

        startActivity(cambio);

    }

}
