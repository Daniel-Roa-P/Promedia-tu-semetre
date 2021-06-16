package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class FinalActivity extends ActivityListas {

    private Button añadir,remover, botConf;
    private int porcentajeRestante,indice = 0;
    private double notaAcumulada;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        res = getResources();
        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFinal);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Obtener faltante");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        botConf = (Button) findViewById(R.id.botonConfigurar);

        lista = new ArrayList<Contenedor>();
        listaNotas = (ListView) findViewById(R.id.listaFinal);
        añadir = (Button) findViewById(R.id.botonAñadir);
        remover = (Button) findViewById(R.id.botonRetirar);

        spinner = (Spinner) findViewById(R.id.eleccionFraseFinal);
        ArrayAdapter opciones = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);
        spinner.setAdapter(opciones);

        spinner.setSelection(pref.getFrase());

        mAdView = (AdView) findViewById(R.id.anuncio6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        advertenciaPocentajes = "la suma de los porcentajes debe estar entre 1 a 100";

        añadir.setOnClickListener(v -> {

            indice++;

            Contenedor con = new Contenedor();

            con.setTexto1("Nota "+ indice +":");
            con.setTexto2("Porcentaje "+":");
            con.setTexto3("%");

            lista.add(con);

            adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,lista);
            listaNotas.setAdapter(adapter);

        });

        botConf.setOnClickListener(v -> cambioConf());

        remover.setOnClickListener(v -> {

            if(lista.size() >1) {

                indice--;

                lista.remove(lista.size() - 1);

                adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios, lista);
                listaNotas.setAdapter(adapter);

            } else {

                Toast.makeText(FinalActivity.this,
                        "minimo debe haber una nota", Toast.LENGTH_LONG).show();

            }

        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                pref.setPreferenciaFrase(spinner.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void cambioConf(){

        Intent cambioConfiguracion = new Intent(this, ActivityConfiguracion.class);

        startActivity(cambioConfiguracion);

    }

    public void calcularFaltante(View view){

        revision();

        denominador = totalPorcentajes;

        if (validacion(FinalActivity.this)) {

            porcentajeRestante = 100 - totalPorcentajes;

            if(porcentajeRestante !=0){

                for(int i=0;i<lista.size();i++){

                    notaAcumulada =  notaAcumulada + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()));

                }

                notaFinal = (( (int) (pref.getMinima()*100)) - notaAcumulada)/porcentajeRestante;

                if(notaFinal <= pref.getMaxima()){

                    textoInicial = "Necesitas un ";
                    textoFinal = " para pasar en el " + porcentajeRestante + " %";

                } else {

                    textoInicial = "Ya perdiste pues necesitas un ";
                    textoFinal = " en el " + porcentajeRestante + "%";

                }

            } else {

                for(int i=0;i<lista.size();i++){

                    notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/100;

                }

                if(notaFinal >= pref.getMinima()){

                    textoInicial = "Ya pasaste con: ";
                    textoFinal = ", pues ingresaste el 100 % de las notas";

                } else {

                    textoInicial = "Ya perdiste con: ";
                    textoFinal = ", pues ingresaste el 100 % de las notas";

                }

            }

            SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
            indicadorFinal = preferenciaFrase.getInt("opcion",0);

            if(indicadorFinal==2){

                indicadorFinal=(int) (2*Math.random());

            }

            eleccionFrases();

            Intent cambio = new Intent(this, ThirdActivity.class);
            cambio.putExtra("textoFrase",textoFrase);
            cambio.putExtra("textoNota", textoNota);
            cambio.putExtra("idImagen",idImagen);

            resetValues();
            notaFinal=0;
            notaAcumulada = 0;

            startActivity(cambio);

        }

    }

    @Override
    protected void eleccionFrases() {

        if(porcentajeRestante != 0) {

            if (notaFinal > pref.getMaxima()) {

                eleccion(res.getStringArray(R.array.frase_final_1));

            } else if (notaFinal >= pref.getMinima() && notaFinal <= pref.getMaxima()) {

                eleccion(res.getStringArray(R.array.frase_final_2));

            } else if (notaFinal >= pref.getMinima()/100 && notaFinal < pref.getMinima()) {

                eleccion(res.getStringArray(R.array.frase_final_3));

            } else if (notaFinal < pref.getMinima()/100 ) {

                eleccion(res.getStringArray(R.array.frase_final_4));

            }

        } else {

            if(notaFinal >= 0 && notaFinal < ((70*pref.getMinima())/100)){

                eleccion(res.getStringArray(R.array.frases1));

            } else if (notaFinal >= ((70*pref.getMinima())/100) && notaFinal < pref.getMinima()) {

                eleccion(res.getStringArray(R.array.frases2));

            } else if (notaFinal >= pref.getMinima() && notaFinal < ((70*pref.getMaxima()/100))) {

                eleccion(res.getStringArray(R.array.frases3));

            } else if (notaFinal >= ((70*pref.getMaxima()/100)) && notaFinal < ((90*pref.getMaxima()/100))) {

                eleccion(res.getStringArray(R.array.frases4));

            } else if (notaFinal >= ((90*pref.getMaxima()/100)) && notaFinal <= pref.getMaxima()) {

                eleccion(res.getStringArray(R.array.frases5));

            }

        }

    }
}
