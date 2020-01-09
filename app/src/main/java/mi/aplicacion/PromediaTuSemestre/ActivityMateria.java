package mi.aplicacion.PromediaTuSemestre;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ActivityMateria extends AppCompatActivity {

    private EditText cantidad;
    private AdView mAdView;
    private int motivacion=0;
    private Preferencias pref;
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
        setContentView(R.layout.materia_layout);

        spinner = (Spinner) findViewById(R.id.fraseMateria);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);
        spinner.setAdapter(adapter);

        SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
        spinner.setSelection(preferenciaFrase.getInt("opcion",0));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cantidad = (EditText) findViewById(R.id.editText);

        mAdView = (AdView) findViewById(R.id.anuncio1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                SharedPreferences opcion = getSharedPreferences("frase",Context.MODE_PRIVATE);
                SharedPreferences.Editor objetoEditor = opcion.edit();
                objetoEditor.putInt("opcion",spinner.getSelectedItemPosition());
                objetoEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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
