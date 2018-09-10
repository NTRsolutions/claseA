package com.apreciasoft.admin.remisclasea.Services;

import com.apreciasoft.admin.remisclasea.Entity.RequetClient;
import com.apreciasoft.admin.remisclasea.Entity.client;
import com.apreciasoft.admin.remisclasea.Entity.clienteFull;
import com.apreciasoft.admin.remisclasea.Entity.login;
import com.apreciasoft.admin.remisclasea.Entity.reporte;
import com.apreciasoft.admin.remisclasea.Entity.token;
import com.apreciasoft.admin.remisclasea.Entity.userFull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Admin on 01-01-2017.
 */

public interface ServicesLoguin {

    @Headers("Content-Type: application/json")
    @POST("auth")
    Call<userFull> getUser(@Body login user);

    @Headers("Content-Type: application/json")
    @POST("support/add")
    Call<reporte> reporteFalla(@Body reporte falla);

    @Headers("Content-Type: application/json")
    @POST("auth/token")
    Call<Boolean> token(@Body token token);

    @Headers("Content-Type: application/json")
    @POST("auth/updateSocketWeb")
    Call<Boolean> updateSocketWeb(@Body token token);

    @Headers("Content-Type: application/json")
    @POST("auth/updateClientLiteMobil")
    Call<client> updateClientLiteMobil(@Body clienteFull cl);

    @Headers("Content-Type: application/json")
    @GET("client/find/{id}")
    Call<RequetClient> find(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("auth/checkVersion/{ver}")
    Call<Boolean> checkVersion(@Path("ver") String ver);
}
