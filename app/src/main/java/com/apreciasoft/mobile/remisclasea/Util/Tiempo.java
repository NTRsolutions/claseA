package com.apreciasoft.mobile.remisclasea.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jorge gutierrez on 16/04/2017.
 */

public class Tiempo  {
    public SharedPreferences.Editor editor;
    public static SharedPreferences pref;


    private Timer timer = new Timer();

    //Clase interna que funciona como contador
    class Contador extends TimerTask {
        public void run() {
            int t = pref.getInt("time_slepp", 0);
            System.out.println("segundo: " + t);
            editor.putInt("time_slepp", t + 1);
            editor.commit(); // commit changes
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar(Context c)
    {
        pref = c.getSharedPreferences(HttpConexion.instance, 0); // 0 - for private mode
        editor = pref.edit();
        timer = new Timer();
        timer.schedule(new Contador(), 0, 1000);
    }
    //Detiene el contador
    public void Detener() {
        timer.cancel();
    }
    //Metodo que retorna los segundos transcurridos
    public int getSegundos()
    {
        return pref.getInt("time_slepp", 0);
    }
}