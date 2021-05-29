package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    SharedPreferences preferenciaFondo;
    SharedPreferences preferenciaFrase;
    SharedPreferences preferenciaMaxima;
    SharedPreferences preferenciaFormato;

    public Preferencias(Context context){

        preferenciaFondo = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        preferenciaFrase = context.getSharedPreferences("frase",Context.MODE_PRIVATE);
        preferenciaMaxima = context.getSharedPreferences("maxima",Context.MODE_PRIVATE);
        preferenciaFormato = context.getSharedPreferences("formato",Context.MODE_PRIVATE);

    }

    public void setNightModeState(Boolean state){

        SharedPreferences.Editor editor = preferenciaFondo.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();

    }

    public Boolean loadNightModelState (){

        return preferenciaFondo.getBoolean("NightMode",false);

    }

    public void setPreferenciaFrase(int posicion){

        SharedPreferences.Editor objetoEditor = preferenciaFrase.edit();
        objetoEditor.putInt("opcion",posicion);
        objetoEditor.commit();

    }

    public int getFrase(){

        return preferenciaFrase.getInt("opcion",0);

    }

    public void setPreferenciaFormato(int posicion){

        SharedPreferences.Editor objetoEditor = preferenciaFormato.edit();
        objetoEditor.putInt("formato",posicion);
        objetoEditor.commit();

    }

    public int getFormato(){

        return preferenciaFormato.getInt("formato",0);

    }

    public void setPreferenciaMaxima(int max){

        SharedPreferences.Editor objetoEditor = preferenciaMaxima.edit();
        objetoEditor.putFloat("maxima",max);
        objetoEditor.commit();

    }

    public int getMaxima(){

        return preferenciaMaxima.getInt("maxima",0);

    }

}
