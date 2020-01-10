package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private ImageView imagen;
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

        valor.setText(getIntent().getStringExtra("textoNota"));
        frase.setText(getIntent().getStringExtra("textoFrase"));
        new DownLoadImageTask(imagen).execute("https://raw.githubusercontent.com/" +
                "DanielRoa20171020077/Promedia-tu-semetre/master/imagenes/"
                +getIntent().getStringExtra("idImagen")+".png");

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

