package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySemestre extends AppCompatActivity {

    private EditText materias,creditos;
    private AdView mAdView;
    private RadioButton b1,b2,b3;
    private int motivacion;
    public Preferencias pref;

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

        materias = (EditText) findViewById(R.id.materias);
        creditos = (EditText) findViewById(R.id.creditos);

        b1 = (RadioButton)findViewById(R.id.radioButton4);
        b2 = (RadioButton)findViewById(R.id.radioButton5);
        b3 = (RadioButton)findViewById(R.id.radioButton6);

        mAdView = (AdView) findViewById(R.id.anuncio2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void getSemestre(View view){

        if (b1.isChecked() == true) {
            motivacion = 0;
        } else if (b2.isChecked() == true) {
            motivacion = 1;
        } else if (b3.isChecked() == true) {
            motivacion = 2;
        }

        if(  (materias.getText().length()==0 || (creditos.getText().length()==0) ||materias.getText().toString().equals("0") || creditos.getText().toString().equals("0")) ){

            Toast.makeText(ActivitySemestre.this,
                    "Mínimo debe haber un crédito y/o materia", Toast.LENGTH_LONG).show();

        } else if((materias.getText().length()>3) ||(creditos.getText().length()>4) || Integer.parseInt(materias.getText().toString())>20 || Integer.parseInt(creditos.getText().toString())>=1000){

            Toast.makeText(ActivitySemestre.this,
                    "No es posible ver tantos créditos y/o materia en un semestre", Toast.LENGTH_LONG).show();

        } else if (!(b1.isChecked()==true ||b2.isChecked()==true || b3.isChecked()==true )) {

            Toast.makeText(ActivitySemestre.this,
                    "Elija una opción de mensaje", Toast.LENGTH_LONG).show();

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
            primerCambio.putExtra("motivacion", motivacion);
            startActivity(primerCambio);

        }
    }

}
