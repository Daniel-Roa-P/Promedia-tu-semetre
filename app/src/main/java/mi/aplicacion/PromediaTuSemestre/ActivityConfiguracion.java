package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityConfiguracion extends AppCompatActivity {

    private Switch fondo;
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
        setContentView(R.layout.layout_configuracion);

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

    }

    public void restartApp(){

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }

}


