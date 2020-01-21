package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.annotation.NonNull;

public class FinalActivity extends ActivityListas {

    private Button añadir,remover;

    public int indice = 0;
    private int porcentajeRestante;
    private double notaAcumulada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

    }

    public void calcularFaltante(View view){

        for (int i = 0; i < lista.size(); i++) {

            if (!lista.get(i).getNota().equals("") && !lista.get(i).getPorcentaje().equals("")
                    && !(lista.get(i).getPorcentaje().length()>=6) && !(lista.get(i).getNota().equals("."))) {

                if (Double.parseDouble(lista.get(i).getNota()) >= 0 && Double.parseDouble(lista.get(i).getNota()) <= 5) {

                    rango++;

                }

                llenos++;

                totalPorcentajes = totalPorcentajes + Integer.parseInt(lista.get(i).getPorcentaje());
            }

        }

        denominador = totalPorcentajes;

        if (validacion(FinalActivity.this)) {

            porcentajeRestante = 100 - totalPorcentajes;

            SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
            indicadorFinal = preferenciaFrase.getInt("opcion",0);

            if(indicadorFinal==2){

                indicadorFinal=(int) (2*Math.random());

            }

            for(int i=0;i<lista.size();i++){

                notaAcumulada =  notaAcumulada + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()));


            }

            notaFinal = (300.0 - notaAcumulada)/porcentajeRestante;

            Intent cambio = new Intent(this, ThirdActivity.class);
            cambio.putExtra("textoFrase","suma porcentajes" + totalPorcentajes);
            cambio.putExtra("textoNota", "Necesitas un " + df.format(notaFinal) + " Para pasar");
            cambio.putExtra("idImagen","sarcasmoFinal-0-2");

            resetValues();
            notaFinal=0;
            notaAcumulada = 0;

            startActivity(cambio);

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

}
