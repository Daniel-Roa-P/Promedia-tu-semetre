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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class FinalActivity extends ActivityListas {

    private Button añadir,remover;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista = new ArrayList<Contenedor>();
        listaNotas = (ListView) findViewById(R.id.listaFinal);
        añadir = (Button) findViewById(R.id.botonAñadir);
        remover = (Button) findViewById(R.id.botonRetirar);

        spinner = (Spinner) findViewById(R.id.eleccionFraseFinal);
        ArrayAdapter opciones = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);
        spinner.setAdapter(opciones);

        SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
        spinner.setSelection(preferenciaFrase.getInt("opcion",0));

        mAdView = (AdView) findViewById(R.id.anuncio6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        advertenciaPocentajes = "la suma de los porcentajes debe estar entre 1 a 100";

        añadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                indice++;

                Contenedor con = new Contenedor();

                con.setTexto1("Nota "+ indice +":");
                con.setTexto2("Porcentaje "+":");
                con.setTexto3("%");

                lista.add(con);

                adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,lista);
                listaNotas.setAdapter(adapter);

            }
        });

        remover.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(lista.size() >1) {

                    indice--;

                    lista.remove(lista.size() - 1);

                    adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios, lista);
                    listaNotas.setAdapter(adapter);

                } else {

                    Toast.makeText(FinalActivity.this,
                            "minimo debe haber una nota", Toast.LENGTH_LONG).show();

                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                SharedPreferences opcion = getSharedPreferences("frase",Context.MODE_PRIVATE);
                SharedPreferences.Editor objetoEditor = opcion.edit();
                objetoEditor.putInt("opcion",spinner.getSelectedItemPosition());
                objetoEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void calcularFaltante(View view){

        revision();

        denominador = totalPorcentajes;

        if (validacion(FinalActivity.this)) {

            porcentajeRestante = 100 - totalPorcentajes;

            if(porcentajeRestante !=0){

                for(int i=0;i<lista.size();i++){

                    notaAcumulada =  notaAcumulada + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()));
                    textoInicial = "Necesitas un ";
                    textoFinal = " Para pasar";

                }

                notaFinal = (300.0 - notaAcumulada)/porcentajeRestante;

            } else {

                for(int i=0;i<lista.size();i++){

                    notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/100;

                }

                if(notaFinal >= 3){

                    textoInicial = "Ya pasaste con: ";
                    textoFinal = ", pues ingreaste el 100 % de las notas";

                } else {

                    textoInicial = "Ya perdiste con: ";
                    textoFinal = ", pues ingreaste el 100 % de las notas";

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

        if(notaFinal>5){

            eleccion(res.getStringArray(R.array.frase_final_1));

        } else if (notaFinal>=3 && notaFinal<=5) {

            eleccion(res.getStringArray(R.array.frase_final_2));

        } else if (notaFinal>=0.1 && notaFinal<3) {

            eleccion(res.getStringArray(R.array.frase_final_3));

        } else if (notaFinal<0.1) {

            eleccion(res.getStringArray(R.array.frase_final_4));

        }
    }
}
