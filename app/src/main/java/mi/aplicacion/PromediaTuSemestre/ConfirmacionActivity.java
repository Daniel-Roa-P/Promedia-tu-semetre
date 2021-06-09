package mi.aplicacion.PromediaTuSemestre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ConfirmacionActivity extends Activity {

    private Preferencias pref;
    private Button botonCancelar, botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int) (height * .3));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        botonAceptar = (Button) findViewById(R.id.botanAceptar);
        botonCancelar = (Button) findViewById(R.id.botanCancelar);

        botonAceptar.setOnClickListener(v -> {

            pref.setPreferenciaMaxima(Float.parseFloat(getIntent().getStringExtra("Maximo")));
            pref.setPreferenciaMinima(Float.parseFloat(getIntent().getStringExtra("Minimo")));
            pref.setPreferenciaFrase(getIntent().getIntExtra("Frase", 0));
            pref.setPreferenciaFormato(getIntent().getIntExtra("Formato", 0));

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

        });

        botonCancelar.setOnClickListener(v -> {

            finish();

        });

    }
}
