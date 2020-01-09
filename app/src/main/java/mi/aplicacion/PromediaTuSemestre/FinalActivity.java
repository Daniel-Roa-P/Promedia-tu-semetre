package mi.aplicacion.PromediaTuSemestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    private Preferencias pref;

    private ArrayList<Contenedor> notasGuardadas;
    private Button añadir;
    private ListView vista;

    public int indice = 1;

    private ItemListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = new Preferencias(this);

        if(pref.loadNightModelState() == true){

            setTheme(R.style.DarkTheme);

        } else {

            setTheme(R.style.AppTheme);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);

        notasGuardadas = new ArrayList<Contenedor>();
        vista = (ListView) findViewById(R.id.listaFinal);
        añadir = (Button) findViewById(R.id.button8);

        añadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Contenedor con = new Contenedor();
                con.setTexto1(""+indice+"");

                indice++;

                notasGuardadas.add(con);

                adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,notasGuardadas);
                vista.setAdapter(adapter);

            }
        });

    }


    public void generar(){

        Contenedor con = new Contenedor();
        con.setTexto1(""+indice+"");

        indice++;

        notasGuardadas.add(con);

        adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,notasGuardadas);
        vista.setAdapter(adapter);

    }

}
