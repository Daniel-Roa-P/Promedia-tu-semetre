package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;

public class SecondActivity extends ActivityListas {

    private String t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);
        res = getResources();

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSegundo);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Promediar");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        lista = new ArrayList<Contenedor>();
        listaNotas = (ListView) findViewById(R.id.lista);

        mAdView = (AdView) findViewById(R.id.anuncio3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        textoInicial = "Tu promedio es: ";
        textoFinal = "";

        t1 = getIntent().getStringExtra("texto1");
        t2 = getIntent().getStringExtra("texto2");
        t3 = getIntent().getStringExtra("texto3");

        denominador = getIntent().getIntExtra("denominador",100);

        advertenciaPocentajes = "la suma de los "+ t2 +"s debe ser " + denominador;

        for (int i = 1; i <= Integer.parseInt(getIntent().getStringExtra("cantidad")); i++) {
            Contenedor contenedor=new Contenedor();

            contenedor.setTexto1(t1+ i +":");
            contenedor.setTexto2(t2+":");
            contenedor.setTexto3(t3);

            lista.add(contenedor);
        }

        adapter = new ItemListAdapter(SecondActivity.this, R.layout.espacios,lista);
        listaNotas.setAdapter(adapter);

    }

    public void cambiar(View view) {

        revision();

        if (validacion(SecondActivity.this)){

            resetValues();

            for(int i=0;i<lista.size();i++){

                notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/denominador;

            }

            SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
            indicadorFinal = preferenciaFrase.getInt("opcion",0);

            if(indicadorFinal==2){

                indicadorFinal=(int) (2*Math.random());

            }

            eleccionFrases();

            Intent cambio = new Intent(this, ThirdActivity.class);
            cambio.putExtra("textoFrase",textoFrase);
            cambio.putExtra("textoNota",textoNota);
            cambio.putExtra("idImagen",idImagen);

            startActivity(cambio);

        }

    }

    @Override
    protected void eleccionFrases() {

        if(notaFinal>=0 && notaFinal<2){

            eleccion(res.getStringArray(R.array.frases1));

        } else if (notaFinal>=2 && notaFinal<3) {

            eleccion(res.getStringArray(R.array.frases2));

        } else if (notaFinal>=3 && notaFinal<3.5) {

            eleccion(res.getStringArray(R.array.frases3));

        } else if (notaFinal>=3.5 && notaFinal<4.5) {

            eleccion(res.getStringArray(R.array.frases4));

        } else if (notaFinal>=4.5 && notaFinal<=5) {

            eleccion(res.getStringArray(R.array.frases5));

        }

    }
}