package com.apreciasoft.mobile.remisclasea.Http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 01-01-2017.
 */

public class HttpConexion {


    public static String base;
    /*public static String ip = "192.168.0.5";*/public static String ip = "as-nube.com";
    public  static String instance = "clasea";
    public static int portWsWeb = 8091;
    public static int portWsCliente = 3000;
    public  static  String PROTOCOL = "https";
    public  static  String COUNTRY = "ARG";




    public static final String BASE_URL = PROTOCOL+"://"+ip+"/";

    //public static final String BASE_URL = PROTOCOL+"://"+ip+":8888/";

        public static Retrofit retrofit = null;

        public  static void  setBase(String folder)
        {
            HttpConexion.base =  folder;
        }

        public static Retrofit getUri() {


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

            retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL+base+"/Api/index.php/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();

            return retrofit;
        }


}
