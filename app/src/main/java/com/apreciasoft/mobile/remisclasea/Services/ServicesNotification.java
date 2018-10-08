package com.apreciasoft.mobile.remisclasea.Services;

import com.apreciasoft.mobile.remisclasea.Entity.notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by usario on 25/4/2017.
 */

public interface ServicesNotification {

    @Headers("Content-Type: application/json")
    @GET("notifications/find/{id}")
    Call<List<notification>> getNotifications(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("notifications/read/{id}/{idUser}")
    Call<List<notification>> readNotifications(@Path("id") int id,@Path("idUser") int idUser);



}
