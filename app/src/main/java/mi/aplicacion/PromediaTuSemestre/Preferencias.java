package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private SharedPreferences preferenciaFondo;
    private SharedPreferences preferenciaFrase;
    private SharedPreferences preferenciaMaxima;
    private SharedPreferences preferenciaMinima;
    private SharedPreferences preferenciaFormato;

    public Preferencias(Context context){

        preferenciaFondo = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        preferenciaFrase = context.getSharedPreferences("frase",Context.MODE_PRIVATE);
        preferenciaMaxima = context.getSharedPreferences("maxima",Context.MODE_PRIVATE);
        preferenciaMinima = context.getSharedPreferences("minima",Context.MODE_PRIVATE);
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

    public void setPreferenciaMaxima(float max){

        SharedPreferences.Editor objetoEditor = preferenciaMaxima.edit();
        System.out.println(max);
        objetoEditor.putFloat("maxima",max);
        objetoEditor.commit();

    }

    public float getMaxima(){

        return preferenciaMaxima.getFloat("maxima",5);

    }

    public void setPreferenciaMinima(float min){

        SharedPreferences.Editor objetoEditor = preferenciaMinima.edit();
        System.out.println(min);
        objetoEditor.putFloat("minima",min);
        objetoEditor.commit();

    }

    public float getMinima(){

        return preferenciaMinima.getFloat("minima",3);

    }

}
