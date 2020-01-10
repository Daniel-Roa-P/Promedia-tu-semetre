package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ItemListAdapter adapter;
    private ListView listaNotas;
    private int indicadorFinal = 0;
    private int cuadros,llenos,totalPorcentajes,indicador;
    private double rango,denominador;
    private String t1,t2,t3,textoNota,textoFrase,idImagen;
    private double notaFinal = 0;
    private AdView mAdView;
    private DecimalFormat df = new DecimalFormat("#.000");
    private Resources res;
    public Preferencias pref;

    public ArrayList<Contenedor>lista;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista=new ArrayList<Contenedor>();
        listaNotas =(ListView) findViewById(R.id.lista);

        mAdView = (AdView) findViewById(R.id.anuncio3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        cuadros = Integer.parseInt(getIntent().getStringExtra("cantidad"));

        t1 = getIntent().getStringExtra("texto1");
        t2 = getIntent().getStringExtra("texto2");
        t3 = getIntent().getStringExtra("texto3");

        denominador = getIntent().getIntExtra("denominador",100);
        indicador = getIntent().getIntExtra("motivacion",0);

        for (int i = 1; i <= cuadros; i++) {
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

        for (int i = 0; i < lista.size(); i++) {

            if (!lista.get(i).getNota().equals("") && !lista.get(i).getPorcentaje().equals("") && !(lista.get(i).getPorcentaje().length()>=6) && !(lista.get(i).getNota().equals("."))) {

                if (Double.parseDouble(lista.get(i).getNota()) >= 0 && Double.parseDouble(lista.get(i).getNota()) <= 5) {

                    rango++;

                }

                llenos++;

                totalPorcentajes = totalPorcentajes + Integer.parseInt(lista.get(i).getPorcentaje());
            }

        }

        if (!(llenos == cuadros)) {

            Toast.makeText(SecondActivity.this,
                    "Por favor llene bien todos los campos de texto", Toast.LENGTH_LONG).show();

            llenos = 0;
            totalPorcentajes = 0;
            rango = 0;

        } else if(!(totalPorcentajes == denominador)) {

            Toast.makeText(SecondActivity.this,
                    "la suma de los "+ t2 +"s debe ser " + denominador, Toast.LENGTH_LONG).show();

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

            for(int i=0;i<lista.size();i++){

                notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/denominador;

            }

            SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
            indicadorFinal = preferenciaFrase.getInt("opcion",0);

            if(indicadorFinal==2){

                indicadorFinal=(int) (2*Math.random());

            }

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

            Intent cambio = new Intent(this, ThirdActivity.class);
            cambio.putExtra("textoFrase",textoFrase);
            cambio.putExtra("textoNota",textoNota);
            cambio.putExtra("idImagen",idImagen);

            startActivity(cambio);
        }

    }

    public void eleccion(String[] seleccionada){

        int indice = (int) ((seleccionada.length/4)*Math.random());
        indice = (indice*2)+((seleccionada.length/2)*indicadorFinal);

        textoNota = "Tu promedio es: "+ df.format(notaFinal);
        textoFrase = seleccionada[indice];
        idImagen = seleccionada[indice+1];

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
