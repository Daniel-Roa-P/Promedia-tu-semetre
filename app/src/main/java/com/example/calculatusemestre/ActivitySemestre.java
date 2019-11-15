package com.example.calculatusemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        if((Integer.parseInt(materias.getText().toString())==0) || (materias.getText().equals("") || Integer.parseInt(creditos.getText().toString())==0) || (creditos.getText().equals(""))){

            Toast.makeText(ActivitySemestre.this,
                    "Minimo debe haber un credito y/o materia", Toast.LENGTH_LONG).show();

        } else if((materias.getText().length()>3) ||(creditos.getText().length()>4) || Integer.parseInt(materias.getText().toString())>21 || Integer.parseInt(creditos.getText().toString())>1000){

            Toast.makeText(ActivitySemestre.this,
                    "No es posible ver tantos creditos y/o materia en un semestre", Toast.LENGTH_LONG).show();

        } else {

            Intent primerCambio = new Intent(this, SecondActivity.class);

            primerCambio.putExtra("cantidad", materias.getText().toString());
            primerCambio.putExtra("texto1", "Materia ");
            primerCambio.putExtra("texto2", "Credito");
            primerCambio.putExtra("texto3", "");
            primerCambio.putExtra("denominador", Integer.parseInt(creditos.getText().toString()));
            startActivity(primerCambio);

        }
    }

}
