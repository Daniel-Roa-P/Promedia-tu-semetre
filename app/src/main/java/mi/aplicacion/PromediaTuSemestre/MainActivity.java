package mi.aplicacion.PromediaTuSemestre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private Preferencias pref;
    private Button botConf;
    private int contador;

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

        botConf = (Button) findViewById(R.id.botonConfigurar);
        botConf.setVisibility(View.INVISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Promedia tu semestre");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

    public void enviarFinal(View view){

        Intent finalCambio = new Intent(this, FinalActivity.class);

        startActivity(finalCambio);

    }

    @Override
    public void onBackPressed(){

        if(contador >= 1) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            Toast.makeText(this, "Presione de nuevo para salir de la aplicación", Toast.LENGTH_SHORT).show();
            contador++;

        }

    }


}
