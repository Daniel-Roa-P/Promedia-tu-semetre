package mi.aplicacion.PromediaTuSemestre;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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


public class ActivityMateria extends AppCompatActivity {

    private EditText cantidad;
    private AdView mAdView;
    private Preferencias pref;
    private Spinner spinner;
    private Button botConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_layout);

        spinner = (Spinner) findViewById(R.id.fraseMateria);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(pref.getFrase());

        cantidad = (EditText) findViewById(R.id.editText);

        mAdView = (AdView) findViewById(R.id.anuncio1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMateria);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Promediar materia");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        botConf = (Button) findViewById(R.id.botonConfigurar);

        botConf.setOnClickListener(v -> cambioConf());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                pref.setFrase(spinner.getSelectedItemPosition());

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

    public void getNota(View view) {

        if (cantidad.getText().length() == 0 || cantidad.getText().toString().equals("0")) {

            Toast.makeText(ActivityMateria.this,
                    "Mínimo debe haber una nota", Toast.LENGTH_LONG).show();

        } else if ((cantidad.getText().length() > 3 || Integer.parseInt(cantidad.getText().toString()) > 100)) {

            Toast.makeText(ActivityMateria.this,
                    "El máximo  de notas posible es de 99", Toast.LENGTH_LONG).show();

        } else {

            Intent primerCambio = new Intent(this, SecondActivity.class);

            primerCambio.putExtra("cantidad", cantidad.getText().toString());
            primerCambio.putExtra("texto1", "Nota ");
            primerCambio.putExtra("texto2", "Porcentaje");
            primerCambio.putExtra("texto3", "%");
            primerCambio.putExtra("denominador", 100);
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
