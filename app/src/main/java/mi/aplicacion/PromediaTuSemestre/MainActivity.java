package mi.aplicacion.PromediaTuSemestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private Preferencias pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.anuncio5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    public void enviarMateria(View view){

        Intent primerCambio = new Intent(this, ActivityMateria.class);

        startActivity(primerCambio);

    }

    public void enviarSemestre(View view){

        Intent segundoCambio = new Intent(this, ActivitySemestre.class);

        startActivity(segundoCambio);

    }

    public void enviarConfiguracion(View view){

        Intent tercerCambio = new Intent(this, ActivityConfiguracion.class);

        startActivity(tercerCambio);

    }



}
