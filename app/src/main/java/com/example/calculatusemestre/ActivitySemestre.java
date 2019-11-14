package com.example.calculatusemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySemestre extends AppCompatActivity {

    private EditText materias,creditos;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semestre_layout);

        materias = (EditText) findViewById(R.id.materias);
        creditos = (EditText) findViewById(R.id.creditos);

        mAdView = (AdView) findViewById(R.id.anuncio2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void getSemestre(View view){

        Intent primerCambio = new Intent(this, SecondActivity.class);

        primerCambio.putExtra("cantidad",materias.getText().toString());
        primerCambio.putExtra("texto1","Materia ");
        primerCambio.putExtra("texto2","Creditos: ");
        primerCambio.putExtra("texto3","");
        startActivity(primerCambio);

    }

}
