package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityListas extends AppCompatActivity {

    protected Preferencias pref;
    protected ArrayList<Contenedor> lista;
    protected ListView listaNotas;
    protected ItemListAdapter adapter;
    protected AdView mAdView;
    protected DecimalFormat df = new DecimalFormat("0.0");
    protected Resources res;
    protected String textoNota, textoFrase, idImagen, advertenciaPocentajes;
    protected double notaFinal = 0;
    protected int indicadorFinal = 0;
    protected int llenos, totalPorcentajes;
    protected double rango;
    protected double denominador;
    protected String textoInicial, textoFinal;

    public void eleccion(String[] seleccionada) {

        int indice = (int) ((seleccionada.length / 4) * Math.random());
        indice = (indice * 2) + ((seleccionada.length / 2) * indicadorFinal);

        if(pref.getFormato() == 0){

            textoNota =  textoInicial + df.format(notaFinal) + textoFinal;

        } else {

            textoNota =  textoInicial + Math.round(notaFinal) + textoFinal;

        }

        textoFrase = seleccionada[indice];
        idImagen = seleccionada[indice + 1];

        notaFinal = 0;

    }

    protected Boolean validacion(Context contexto) {

        if (!(llenos == lista.size())) {

            Toast.makeText(contexto,
                    "Por favor llene bien todos los campos de texto", Toast.LENGTH_LONG).show();

            resetValues();
            return false;

        } else if (!(rango == lista.size())) {

            Toast.makeText(contexto,
                    "ingrese las notas en el rango indicado", Toast.LENGTH_LONG).show();

            resetValues();
            return false;

        } else if((!(totalPorcentajes == denominador)) || ((totalPorcentajes > 100 || (totalPorcentajes < 1)))) {

            Toast.makeText(contexto,
                advertenciaPocentajes, Toast.LENGTH_LONG).show();

            resetValues();
            return false;

    }

        return true;

    }

    protected void resetValues(){

        llenos = 0;
        totalPorcentajes = 0;
        rango = 0;

    }

    protected void revision(){

        for (int i = 0; i < lista.size(); i++) {

            if (!lista.get(i).getNota().equals("") && !lista.get(i).getPorcentaje().equals("")
                    && !(lista.get(i).getPorcentaje().length()>=6) && !(lista.get(i).getNota().equals("."))) {

                if (Double.parseDouble(lista.get(i).getNota()) >= 0 && Double.parseDouble(lista.get(i).getNota()) <= pref.getMaxima()) {

                    rango++;

                }

                llenos++;

                totalPorcentajes = totalPorcentajes + Integer.parseInt(lista.get(i).getPorcentaje());

            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    protected void eleccionFrases(){};

}
