package mi.aplicacion.PromediaTuSemestre;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<Contenedor>{


    private ArrayList<Contenedor> items;
    private int identificadorLayout;
    private Context contexto;

    public ItemListAdapter(Context contexto, int identificadorLayout, ArrayList<Contenedor> items){

        super(contexto, identificadorLayout, items);
        this.identificadorLayout = identificadorLayout;
        this.contexto = contexto;
        this.items = items;

    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Holder holder;

        LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
        row = inflater.inflate(identificadorLayout, parent, false);

        holder = new Holder();
        holder.contenedor = items.get(position);

        holder.nota = (EditText)row.findViewById(R.id.nota);
        holder.porcentaje = (EditText)row.findViewById(R.id.porcentaje);
        holder.texto1 = (TextView)row.findViewById(R.id.primerTexto);
        holder.texto2 = (TextView)row.findViewById(R.id.segundoTexto);
        holder.texto3 = (TextView)row.findViewById(R.id.simbolo);

        setNotaTextChangeListener(holder);
        setPorcentajeTextListeners(holder);

        row.setTag(holder);

        setupItem(holder);

        return row;
    }

    private void setupItem(Holder holder)
    {
        holder.nota.setText(String.valueOf(holder.contenedor.getNota()));
        holder.porcentaje.setText(String.valueOf(holder.contenedor.getPorcentaje()));
        holder.texto1.setText(String.valueOf(holder.contenedor.getTexto1()));
        holder.texto2.setText(String.valueOf(holder.contenedor.getTexto2()));
        holder.texto3.setText(String.valueOf(holder.contenedor.getTexto3()));
    }

    public static class Holder
    {
        Contenedor contenedor;
        EditText nota;
        EditText porcentaje;
        TextView texto1;
        TextView texto2;
        TextView texto3;

    }

    private void setNotaTextChangeListener(final Holder holder){

        holder.nota.addTextChangedListener(new TextWatcher(){

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() > 0) {
                    holder.contenedor.setNota(s.toString());
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }

        });
    }

    private void setPorcentajeTextListeners(final Holder holder) {

        holder.porcentaje.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() > 0) {
                    holder.contenedor.setPorcentaje(s.toString());
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }

            @Override
            public void afterTextChanged(Editable s) { }

        });
    }

}
