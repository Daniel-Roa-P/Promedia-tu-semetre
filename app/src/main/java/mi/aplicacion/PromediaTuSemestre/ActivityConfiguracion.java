package mi.aplicacion.PromediaTuSemestre;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ActivityConfiguracion extends AppCompatActivity {

    private Switch fondo;
    private ConstraintLayout cajon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_configuracion);

        fondo = (Switch) findViewById(R.id.fondo);
        cajon = (ConstraintLayout) findViewById(R.id.cajon);

        Resources resources = getResources();

        fondo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    cajon.setBackgroundColor(Color.BLACK);

                } else {

                    cajon.setBackgroundColor(Color.WHITE);

                }

            }

        });

    }

}


