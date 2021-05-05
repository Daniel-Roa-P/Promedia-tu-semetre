package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private ImageView imagen;
    private AdView mAdView;
    private InterstitialAd anuncioPantalla;
    private Button regresoInicio;
    public Preferencias pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Resources res = getResources();

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
        
        int resourceID = getResources().getIdentifier(
                getIntent().getStringExtra("idImagen") + "min",
                "raw",
                getPackageName()
        );

        Glide.with(this).load(resourceID).into(imagen);

        regresoInicio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent cambio = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(cambio);

                if (anuncioPantalla.isLoaded()) {

                    anuncioPantalla.show();

                }
            }
        });

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

