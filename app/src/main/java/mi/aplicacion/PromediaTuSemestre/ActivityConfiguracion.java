package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityConfiguracion extends AppCompatActivity {

    private Switch fondo;
    private Preferencias pref;
    private Spinner spinner, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_configuracion);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);

        spinner = (Spinner) findViewById(R.id.frase);
        spinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.formatos, R.layout.elementos_spinner);

        spinner2 = (Spinner) findViewById(R.id.formatoNota);
        spinner2.setAdapter(adapter);

        SharedPreferences preferenciaFrase = getSharedPreferences("frase", Context.MODE_PRIVATE);
        spinner.setSelection(preferenciaFrase.getInt("opcion",0));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarConfig);
        TextView textoToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textoToolbar.setText("Configuracion");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fondo = (Switch) findViewById(R.id.fondo);

        if(pref.loadNightModelState() == true){

            fondo.setChecked(true);

        }

        fondo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    pref.setNightModeState(true);
                    restartApp();

                } else {

                    pref.setNightModeState(false);
                    restartApp();

                }



            }

        });

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

    public void restartApp(){

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

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


