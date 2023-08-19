package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private ImageView imagen;
    private AdView mAdView;
    private InterstitialAd anuncioPantalla;
    private Button regresoInicio, botConf;
    private Preferencias pref;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if (pref.loadNightModelState() == true) {

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTercero);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Resultado");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        botConf = (Button) findViewById(R.id.botonConfigurar);

        valor = (TextView) findViewById(R.id.notaFinal);
        imagen = (ImageView) findViewById(R.id.imageView2);
        frase = (TextView) findViewById(R.id.frase);
        regresoInicio = (Button) findViewById(R.id.button5);

        mAdView = (AdView) findViewById(R.id.anuncio4);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        AdRequest adRequest2 = new AdRequest.Builder().build();

        anuncioPantalla.load(this,"ca-app-pub-8837572421295884/8218840917", adRequest2,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        anuncioPantalla = interstitialAd;
                        Log.i("TAG", "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        anuncioPantalla = null;
                        Log.d("TAG", loadAdError.toString());

                    }
                });

        valor.setText(getIntent().getStringExtra("textoNota"));
        frase.setText(getIntent().getStringExtra("textoFrase"));

        String nombreImagen = "Imagenes/" + getIntent().getStringExtra("idImagen") + "min.png";

        storageReference = FirebaseStorage.getInstance().getReference().child(nombreImagen);

        try{

            final File localFile = File.createTempFile(getIntent().getStringExtra("idImagen"), "png");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imagen.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                }
            });

        } catch (IOException e){

            e.printStackTrace();

        }

        regresoInicio.setOnClickListener(v -> {

            Intent cambio = new Intent(ThirdActivity.this, MainActivity.class);
            startActivity(cambio);

            if (anuncioPantalla != null) {
                anuncioPantalla.show(ThirdActivity.this);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }

        });

        botConf.setOnClickListener(v -> cambioConf());

    }

    public void cambioConf(){

        Intent cambioConfiguracion = new Intent(this, ActivityConfiguracion.class);

        startActivity(cambioConfiguracion);

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

