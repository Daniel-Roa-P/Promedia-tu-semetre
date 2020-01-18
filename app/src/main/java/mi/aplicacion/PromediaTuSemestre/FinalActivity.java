package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    private Preferencias pref;

    private ArrayList<Contenedor> lista;
    private Button añadir,remover;
    private ListView vista;

    public int indice = 0;
    private int llenos,totalPorcentajes,porcentajeRestante;
    private double notaAcumulada;
    private double rango;
    private DecimalFormat df = new DecimalFormat("#.000");

    private ItemListAdapter adapter;


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
        vista = (ListView) findViewById(R.id.listaFinal);
        añadir = (Button) findViewById(R.id.botonAñadir);
        remover = (Button) findViewById(R.id.botonRetirar);

        añadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                indice++;

                Contenedor con = new Contenedor();

                con.setTexto1("Nota "+ indice +":");
                con.setTexto2("Porcentaje "+":");
                con.setTexto3("%");

                lista.add(con);

                adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,lista);
                vista.setAdapter(adapter);

            }
        });

        remover.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(lista.size() >1) {

                    indice--;

                    lista.remove(lista.size() - 1);

                    adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios, lista);
                    vista.setAdapter(adapter);

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

        if (!(llenos == lista.size())) {

            Toast.makeText(FinalActivity.this,
                    "Por favor llene bien todos los campos de texto", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else if( (totalPorcentajes > 100) || (totalPorcentajes < 1)) {

            Toast.makeText(FinalActivity.this,
                    "la suma de los porcentajes debe estar entre 1 a 100", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else if(!(rango==lista.size())) {

            Toast.makeText(FinalActivity.this,
                    "ingrese las notas en el rango indicado", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else {

            porcentajeRestante = 100 - totalPorcentajes;

            for(int i=0;i<lista.size();i++){

                notaAcumulada =  notaAcumulada + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()));


            }

            Intent cambio = new Intent(this, ThirdActivity.class);
            cambio.putExtra("textoFrase","Necesitas un " + df.format((300.0 - notaAcumulada)/porcentajeRestante) + " Para pasar");
            cambio.putExtra("textoNota","suma porcentajes" + totalPorcentajes);
            cambio.putExtra("idImagen","sarcasmo-0-2");

            llenos=0;
            rango=0;
            notaAcumulada = 0;
            totalPorcentajes = 0;

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
