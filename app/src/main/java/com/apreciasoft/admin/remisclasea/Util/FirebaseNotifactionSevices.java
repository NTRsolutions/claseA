package com.apreciasoft.admin.remisclasea.Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.apreciasoft.admin.remisclasea.Activity.HomeActivity;
import com.apreciasoft.admin.remisclasea.Activity.HomeClientActivity;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntityLite;
import com.apreciasoft.admin.remisclasea.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

/**
 * Created by jorge gutierrez on 13/02/2017.
 */

public class FirebaseNotifactionSevices extends FirebaseMessagingService {


    public static final String TAG = "NOTICIAS";
    public GlovalVar gloval;
    public Uri soundUri;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        try {

        String from = remoteMessage.getFrom();
            Log.d(TAG, "Notificación: " + from);

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notificación: " + remoteMessage.getNotification().getBody());

        }

        if (remoteMessage.getData().size() > 0) {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            gloval = ((GlovalVar) getApplicationContext());


                gloval.setGv_travel_current_lite(gson.fromJson(gson.toJson(remoteMessage.getData()), InfoTravelEntityLite.class));


            Intent intent = new Intent("update-message");
           // intent.putExtra("message", gson.fromJson(gson.toJson(remoteMessage.getData()), InfoTravelEntity.class));
            intent.putExtra("message", "CANGUE INFO FIREBASE (ASREMIS)");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


                if (gloval.getGv_id_profile() == 2 || gloval.getGv_id_profile() == 5) {
                    Log.d("Notificación", String.valueOf("YESS 2,5"));

                mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), HomeClientActivity.class);

                } else {
                    Log.d("Notificación", String.valueOf("YESS DRIVER"));

                mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), HomeActivity.class);

            }


            }
        }catch (Exception e){
            Log.d("ERROR" , e.getMessage());
        }

    }

    private void mostrarNotificacion(String title, String body,Class<?> cls) {

        /* Limpiamos las notificaciones*/
        NotificationManager notifManager= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancelAll();



        Context context = this;

        Intent intent;

        if(gloval.getGv_id_profile() == 2 || gloval.getGv_id_profile() == 5)
        {
             intent = new Intent(context, HomeClientActivity.class);
        }else
        {
             intent = new Intent(context, HomeActivity.class);
        }





        //intent.putExtra(".HomeActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


       // try {
            // Perform the operation associated with our pendingIntent
           // pendingIntent.send();
            Log.d("NOTIFICATE", "Abrir");
        //} catch (PendingIntent.CanceledException e) {
          //  e.printStackTrace();
        //}

        String sound = gloval.getGv_travel_current_lite().getSound().toString();
        Log.d("NOTIFICATE", sound);


        if(sound != null) {
            switch (sound) {
                case "nuevareserva":
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.nuevareserva);//Here is FILE_NAME is the name of file that you want to play
                    break;
                case "nuevoviaje":
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.nuevoviaje);//Here is FILE_NAME is the name of file that you want to play
                    break;
                case "remis":
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.remis);//Here is FILE_NAME is the name of file that you want to play
                    break;
                case "remis2":
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.remis2);//Here is FILE_NAME is the name of file that you want to play
                    break;
                case "tienesreserva":
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.tienesreserva);//Here is FILE_NAME is the name of file that you want to play
                    break;
                default:
                    soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.remis);//Here is FILE_NAME is the name of file that you want to play

            }
        }else{
            soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.remis);//Here is FILE_NAME is the name of file that you want to play
        }


        Log.d("Notificación", String.valueOf(soundUri));


        if(gloval.getGv_travel_current_lite() != null)
        {
           if(gloval.getGv_travel_current_lite().getNameOrigin() != null) {

                Log.d("Notificación", String.valueOf("FINAL"));

                // Patrón de vibración: 1 segundo vibra, 0.5 segundos para, 1 segundo vibra
                 long[] pattern = new long[]{1000,500,1000};




                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setContentTitle(title)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText(gloval.getGv_travel_current_lite().getNameOrigin())
                        .setAutoCancel(true)
                        .setSound(soundUri)
                        .setContentIntent(pendingIntent);

                // Uso en API 11 o mayor
                notificationBuilder.setVibrate(pattern);


                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationBuilder.build());




            }
        }



    }
}
