package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivitySemestre extends AppCompatActivity {

    private EditText materias,creditos;
    private AdView mAdView;
    private Button botConf;
    public Preferencias pref;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.semestre_layout);

        spinner = (Spinner) findViewById(R.id.fraseSemestre);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(pref.getFrase());

        materias = (EditText) findViewById(R.id.materias);
        creditos = (EditText) findViewById(R.id.creditos);

        mAdView = (AdView) findViewById(R.id.anuncio2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSemestre);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Promediar semestre");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        botConf = (Button) findViewById(R.id.botonConfigurar);

        botConf.setOnClickListener(v -> cambioConf());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                pref.setPreferenciaFrase(spinner.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void cambioConf(){

        Intent cambioConfiguracion = new Intent(this, ActivityConfiguracion.class);

        startActivity(cambioConfiguracion);

    }

    public void getSemestre(View view){

        if(  (materias.getText().length()==0 || (creditos.getText().length()==0) ||materias.getText().toString().equals("0") || creditos.getText().toString().equals("0")) ){

            Toast.makeText(ActivitySemestre.this,
                    "Mínimo debe haber un crédito y/o materia", Toast.LENGTH_LONG).show();

        } else if((materias.getText().length()>3) ||(creditos.getText().length()>4) || Integer.parseInt(materias.getText().toString())>20 || Integer.parseInt(creditos.getText().toString())>=1000){

            Toast.makeText(ActivitySemestre.this,
                    "No es posible ver tantos créditos y/o materia en un semestre", Toast.LENGTH_LONG).show();

        } else if ((Integer.parseInt(materias.getText().toString())) > (Integer.parseInt(creditos.getText().toString()))) {

            Toast.makeText(ActivitySemestre.this,
                    "No pueden haber más materias que créditos", Toast.LENGTH_LONG).show();

        }else {

            Intent primerCambio = new Intent(this, SecondActivity.class);

            primerCambio.putExtra("cantidad", materias.getText().toString());
            primerCambio.putExtra("texto1", "Materia ");
            primerCambio.putExtra("texto2", "Crédito");
            primerCambio.putExtra("texto3", "");
            primerCambio.putExtra("denominador", Integer.parseInt(creditos.getText().toString()));
            startActivity(primerCambio);

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
