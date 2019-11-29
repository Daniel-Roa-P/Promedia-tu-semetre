package mi.aplicacion.PromediaTuSemestre;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ActivityMateria extends AppCompatActivity {

    private EditText cantidad;
    private AdView mAdView;
    private RadioButton b1,b2,b3;
    private int motivacion=0;
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
        setContentView(R.layout.materia_layout);

        cantidad = (EditText) findViewById(R.id.editText);

        b1 = (RadioButton)findViewById(R.id.radioButton);
        b2 = (RadioButton)findViewById(R.id.radioButton2);
        b3 = (RadioButton)findViewById(R.id.radioButton3);

        mAdView = (AdView) findViewById(R.id.anuncio1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void getNota(View view) {

        if (b1.isChecked() == true) {
            motivacion = 0;
        } else if (b2.isChecked() == true) {
            motivacion = 1;
        } else if (b3.isChecked() == true) {
            motivacion = 2;
        }

        if (cantidad.getText().length() == 0 || cantidad.getText().toString().equals("0")) {

            Toast.makeText(ActivityMateria.this,
                    "Mínimo debe haber una nota", Toast.LENGTH_LONG).show();

        } else if ((cantidad.getText().length() > 3 || Integer.parseInt(cantidad.getText().toString()) > 100)) {

            Toast.makeText(ActivityMateria.this,
                    "El máximo  de notas posible es de 99", Toast.LENGTH_LONG).show();

        } else if (!(b1.isChecked()==true ||b2.isChecked()==true || b3.isChecked()==true )) {

            Toast.makeText(ActivityMateria.this,
                    "Elija una opción de mensaje", Toast.LENGTH_LONG).show();

        } else {

            Intent primerCambio = new Intent(this, SecondActivity.class);

            primerCambio.putExtra("cantidad", cantidad.getText().toString());
            primerCambio.putExtra("texto1", "Nota ");
            primerCambio.putExtra("texto2", "Porcentaje");
            primerCambio.putExtra("texto3", "%");
            primerCambio.putExtra("denominador", 100);
            primerCambio.putExtra("motivacion", motivacion);
            startActivity(primerCambio);

        }
    }

}
