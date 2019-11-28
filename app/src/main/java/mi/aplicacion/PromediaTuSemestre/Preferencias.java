package mi.aplicacion.PromediaTuSemestre;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    SharedPreferences misPreferencias;

    public Preferencias(Context context){

        misPreferencias = context.getSharedPreferences("filename",Context.MODE_PRIVATE);

    }

    public void setNightModeState(Boolean state){

        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();

    }

    public Boolean loadNightModelState (){

        Boolean state = misPreferencias.getBoolean("NightMode",false);
        return state;

    }

}
