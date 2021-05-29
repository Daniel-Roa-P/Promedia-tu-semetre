package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityConfiguracion extends AppCompatActivity {

    private Switch fondo;
    private Preferencias pref;
    private Spinner spinner, spinner2;
    private Button botConf, botonConfirmar;
    private EditText editMax, editMin;

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

        editMax = (EditText) findViewById(R.id.notaMaxima);
        editMin = (EditText) findViewById(R.id.notaMinima);

        botConf = (Button) findViewById(R.id.botonConfigurar);
        botonConfirmar = (Button) findViewById(R.id.button2);

        botConf.setVisibility(View.INVISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.elementos, R.layout.elementos_spinner);

        spinner = (Spinner) findViewById(R.id.frase);
        spinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.formatos, R.layout.elementos_spinner);

        spinner2 = (Spinner) findViewById(R.id.formatoNota);
        spinner2.setAdapter(adapter);

        spinner.setSelection(pref.getFrase());
        spinner2.setSelection(pref.getFormato());

        cambiarFormato(spinner2.getSelectedItemPosition());

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

                pref.setPreferenciaFrase(spinner.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                cambiarFormato(spinner2.getSelectedItemPosition());

                pref.setPreferenciaFormato(spinner2.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        botonConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(editMax.getText().length() == 0 || editMin.getText().length() == 0){

                    Toast.makeText(ActivityConfiguracion.this,
                            "Por favor llene todos los campos", Toast.LENGTH_LONG).show();

                } else if(editMax.getText().toString().equals(editMin.getText().toString())){

                    Toast.makeText(ActivityConfiguracion.this,
                            "La nota maxima y minima no pueden ser la misma", Toast.LENGTH_LONG).show();

                } else if(Integer.parseInt(editMin.getText().toString()) > Integer.parseInt(editMax.getText().toString())){

                    Toast.makeText(ActivityConfiguracion.this,
                            "La nota minima no puede ser mayor que la nota maxima", Toast.LENGTH_LONG).show();

                } else {

                    Intent i = new Intent(getApplicationContext(), ConfirmacionActivity.class);

                    startActivity(i);

                }

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

    private void cambiarFormato(int posicion){

        if(posicion == 0){

            editMax.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editMin.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        } else {

            editMax.setInputType(InputType.TYPE_CLASS_NUMBER);
            editMin.setInputType(InputType.TYPE_CLASS_NUMBER);

        }

    }

}


