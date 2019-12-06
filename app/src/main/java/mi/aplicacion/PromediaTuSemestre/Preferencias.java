package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    SharedPreferences preferenciaFondo;

    public Preferencias(Context context){

        preferenciaFondo = context.getSharedPreferences("filename",Context.MODE_PRIVATE);

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

}
