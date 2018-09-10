package com.apreciasoft.admin.remisclasea.Util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.apreciasoft.admin.remisclasea.Activity.MainActivity;
import com.apreciasoft.admin.remisclasea.Entity.token;
import com.apreciasoft.admin.remisclasea.Entity.tokenFull;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.Services.ServicesLoguin;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge gutierrez on 13/02/2017.
 */

public class FirebaseInstanceIdSevices extends FirebaseInstanceIdService {

    public static final String TAG = "NOTICIAS";
    ServicesLoguin apiService = null;
    public GlovalVar gloval;



    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "***Refreshed token*** " + token);
        enviarTokenAlServidor(token);
    }

    private void enviarTokenAlServidor(String _str_token) {
        // Enviar token al servidor


        if(gloval != null) {
            this.apiService = HttpConexion.getUri().create(ServicesLoguin.class);


            token T = new token();


            T.setToken(new tokenFull(_str_token, gloval.getGv_user_id(), gloval.getGv_id_driver(), MainActivity.version));

            Call<Boolean> call = this.apiService.token(T);


            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(T));

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                }

                public void onFailure(Call<Boolean> call, Throwable t) {


                    Log.d("**", t.getMessage());
                }
            });
        }


    }
}
