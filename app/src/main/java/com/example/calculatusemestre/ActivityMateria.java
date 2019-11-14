package com.example.calculatusemestre;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ActivityMateria extends AppCompatActivity {

    private EditText cantidad;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_layout);

        cantidad = (EditText) findViewById(R.id.editText);

        mAdView = (AdView) findViewById(R.id.anuncio1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void getNota(View view){

        Intent primerCambio = new Intent(this, SecondActivity.class);

        primerCambio.putExtra("cantidad",cantidad.getText().toString());
        primerCambio.putExtra("texto1","Nota ");
        primerCambio.putExtra("texto2","Porcentaje");
        primerCambio.putExtra("texto3","%");
        primerCambio.putExtra("denominador",100);
        startActivity(primerCambio);

    }

}
