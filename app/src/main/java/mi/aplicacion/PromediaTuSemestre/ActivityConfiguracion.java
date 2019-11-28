package mi.aplicacion.PromediaTuSemestre;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ActivityConfiguracion extends AppCompatActivity {

    private Switch fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_configuracion);

        fondo = (Switch) findViewById(R.id.fondo);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            fondo.setChecked(true);

        }

        fondo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();

                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();

                }



            }

        });

    }

    public void restartApp(){

        Intent i = new Intent(getApplicationContext(),ActivityConfiguracion.class);
        startActivity(i);
        finish();

    }

}


