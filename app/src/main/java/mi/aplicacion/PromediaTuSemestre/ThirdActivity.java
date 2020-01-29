package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.InputStream;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private ImageView imagen;
    private AdView mAdView;
    private InterstitialAd anuncioPantalla;
    public Preferencias pref;
    private Button regresoInicio;

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
        regresoInicio = (Button) findViewById(R.id.button5);

        mAdView = (AdView) findViewById(R.id.anuncio4);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        anuncioPantalla = new InterstitialAd(this);
        anuncioPantalla.setAdUnitId("ca-app-pub-8837572421295884/8218840917");
        AdRequest adRequest2 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        anuncioPantalla.loadAd(adRequest2);

        valor.setText(getIntent().getStringExtra("textoNota"));
        frase.setText(getIntent().getStringExtra("textoFrase"));
        new DownLoadImageTask(imagen).execute("https://raw.githubusercontent.com/" +
                "DanielRoa20171020077/Promedia-tu-semetre/master/imagenes/"
                +getIntent().getStringExtra("idImagen")+".png");

    regresoInicio.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {

            Intent cambio = new Intent(ThirdActivity.this, MainActivity.class);
            startActivity(cambio);

            if(anuncioPantalla.isLoaded()){

                anuncioPantalla.show();

            }
        }
    });

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

}

