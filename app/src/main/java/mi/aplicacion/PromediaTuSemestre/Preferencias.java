package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    SharedPreferences preferenciaFondo;
    SharedPreferences preferenciaFrase;

    public Preferencias(Context context){

        preferenciaFondo = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        preferenciaFrase = context.getSharedPreferences("frase",Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){

        SharedPreferences.Editor editor = preferenciaFondo.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();

    }

    public Boolean loadNightModelState (){

        Boolean state = preferenciaFondo.getBoolean("NightMode",false);
        return state;

    }

    public void setFrase(int posicion){

        SharedPreferences.Editor objetoEditor = preferenciaFrase.edit();
        objetoEditor.putInt("opcion",posicion);
        objetoEditor.commit();

    }

    public int getFrase(){

        int maximo = preferenciaFrase.getInt("opcion",0);
        return maximo;

    }

}
