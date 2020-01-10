package mi.aplicacion.PromediaTuSemestre;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    private Preferencias pref;

    private ArrayList<Contenedor> notasGuardadas;
    private Button añadir,remover;
    private ListView vista;

    public int indice = 0;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notasGuardadas = new ArrayList<Contenedor>();
        vista = (ListView) findViewById(R.id.listaFinal);
        añadir = (Button) findViewById(R.id.botonAñadir);
        remover = (Button) findViewById(R.id.botonRetirar);

        añadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                indice++;

                Contenedor con = new Contenedor();

                con.setTexto1("Nota "+ indice +":");
                con.setTexto2("Porcentaje "+":");
                con.setTexto3("%");

                notasGuardadas.add(con);

                adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios,notasGuardadas);
                vista.setAdapter(adapter);

            }
        });

        remover.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(notasGuardadas.size() >1) {

                    indice--;

                    notasGuardadas.remove(notasGuardadas.size() - 1);

                    adapter = new ItemListAdapter(FinalActivity.this, R.layout.espacios, notasGuardadas);
                    vista.setAdapter(adapter);

                } else {

                    Toast.makeText(FinalActivity.this,
                            "minimo debe haber una nota", Toast.LENGTH_LONG).show();

                }

            }
        });

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
