package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static mi.aplicacion.PromediaTuSemestre.SecondActivity.lista;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private double notaFinal = 0;
    private ImageView imagen;
    private double denominador=100.0;
    private int indicadorFinal = 0;
    private DecimalFormat df = new DecimalFormat("#.000");
    private AdView mAdView;
    public Preferencias pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Resources res = getResources();

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valor = (TextView) findViewById(R.id.notaFinal);
        imagen = (ImageView) findViewById(R.id.imageView2);
        frase = (TextView) findViewById(R.id.frase);

        mAdView = (AdView) findViewById(R.id.anuncio4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        denominador = getIntent().getDoubleExtra("denominadorFinal",100.0);
        SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
        indicadorFinal = preferenciaFrase.getInt("opcion",0);

        if(indicadorFinal==2){

            indicadorFinal=(int) (2*Math.random());

        }

        for(int i=0;i<lista.size();i++){

            notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/denominador;

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

    }

    public void eleccion(String[] seleccionada) {

        int indice = (int) ((seleccionada.length/4)*Math.random());
        indice = (indice*2)+((seleccionada.length/2)*indicadorFinal);

        valor.setText("Tu promedio es: "+ df.format(notaFinal));
        frase.setText(seleccionada[indice]);
        new DownLoadImageTask(imagen).execute("https://raw.githubusercontent.com/DanielRoa20171020077/Promedia-tu-semetre/master/imagenes/"+seleccionada[indice+1]+".png");

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();

            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

    public void regresoNotas(View view){

        onBackPressed();

    }

    public void regresoInicio(View view){

        Intent cambio = new Intent(this, MainActivity.class);

        startActivity(cambio);

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

