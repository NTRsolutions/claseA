package com.apreciasoft.admin.remisclasea.Services;

import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.Entity.RemisSocketInfo;
import com.apreciasoft.admin.remisclasea.Entity.TraveInfoSendEntity;
import com.apreciasoft.admin.remisclasea.Entity.TravelEntity;
import com.apreciasoft.admin.remisclasea.Entity.TravelLocationEntity;
import com.apreciasoft.admin.remisclasea.Entity.paramEntity;
import com.apreciasoft.admin.remisclasea.Entity.reason;
import com.apreciasoft.admin.remisclasea.Entity.reasonEntity;
import com.apreciasoft.admin.remisclasea.Entity.resp;
import com.apreciasoft.admin.remisclasea.Entity.valuesTravelPreview;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Admin on 05/01/2017.
 */

public interface ServicesTravel {

    @Headers("Content-Type: application/json")
    @GET("travel/getCurrentTravelByIdDriver/{id}")
    Call<InfoTravelEntity> getCurrentTravelByIdDriver(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/getCurrentTravelByIdClient/{id}")
    Call<InfoTravelEntity> getCurrentTravelByIdClient(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/getDriverMapBiIdTravel/{id}")
    Call<TravelLocationEntity> getDriverMapBiIdTravel(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/getCurrentTravelByIdUserCompany/{id}")
    Call<InfoTravelEntity> getCurrentTravelByIdUserCompany(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("travel/add")
    Call<resp> addTravel(@Body TravelEntity user);

    @Headers("Content-Type: application/json")
    @POST("travel/sendPosition")
    Call<RemisSocketInfo> sendPosition(@Body TraveInfoSendEntity travel);

    @Headers("Content-Type: application/json")
    @POST("travel/infoTravelByDriver")
    Call<List<InfoTravelEntity>> infoTravelByDriver(@Body TraveInfoSendEntity filter);

    @Headers("Content-Type: application/json")
    @GET("travel/isRoundTrip/{id}")
    Call<Boolean> isRoundTrip(@Path("id")  int id);

    @Headers("Content-Type: application/json")
    @GET("travel/verifickTravelFinish/{id}")
    Call<Boolean> verifickTravelFinish(@Path("id")  int id);

    @Headers("Content-Type: application/json")
    @GET("travel/verifickTravelCancel/{id}")
    Call<Boolean> verifickTravelCancel(@Path("id")  int id);




    @Headers("Content-Type: application/json")
    @GET("travel/isWait/{id}/{value}")
    Call<Boolean> isWait(@Path("id")  int id,@Path("value")  int value);

    @Headers("Content-Type: application/json")
    @GET("travel/confirmCancelByClient/{id}")
    Call<Boolean> confirmCancelByClient(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/confirmAceptaByClient/{id}")
    Call<Boolean> confirmAceptaByClient(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/calificateDriver/{idTravel}/{start}")
    Call<Boolean> calificateDriver(@Path("idTravel") int idTravel,@Path("start") int start);

    @Headers("Content-Type: application/json")
    @GET("travel/confirmCancelByDriver/{id}")
    Call<Boolean> confirmCancelByDriver(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("travel/finishMobil")
    Call<InfoTravelEntity> finishPost(@Body  TraveInfoSendEntity travel);

    @Headers("Content-Type: application/json")
    @POST("travel/preFinishMobil")
    Call<InfoTravelEntity> preFinishMobil(@Body  TraveInfoSendEntity travel);

    @Headers("Content-Type: application/json")
    @GET("travel/accept/{id}")
    Call<InfoTravelEntity> accept(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/refuse/{id}")
    Call<InfoTravelEntity> refuse(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("travel/init/{id}")
    Call<InfoTravelEntity> init(@Path("id") int id);


    @Headers("Content-Type: application/json")
    @GET("travel/rservations/{idDriver}/{idUserClient}/{idProfileUser}")
    Call<List<InfoTravelEntity>> getReservations(@Path("idDriver") int idDriver,@Path("idUserClient") int idUserClient,@Path("idProfileUser") int idProfileUser);

    @Headers("Content-Type: application/json")
    @GET("travel/readrservations/{id}/{idDriver}")
    Call<List<InfoTravelEntity>> readReservation(@Path("id") int id,@Path("idDriver") int idDriver);


    @Headers("Content-Type: application/json")
    @GET("travel/cacelReservation/{id}/{idDriver}")
    Call<List<InfoTravelEntity>> cacelReservation(@Path("id") int id,@Path("idDriver") int idDriver);

    @Headers("Content-Type: application/json")
    @GET("travel/cancelByClient/{id}/{idReasonCancelKf}")
    Call<Boolean> cancelByClient(@Path("id") int id, @Path("idReasonCancelKf") int idReasonCancelKf );

    @Headers("Content-Type: application/json")
    @GET("travel/reasonForId/{id}")
    Call<reasonEntity> obtIdMotivo(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @GET("config/param")
    Call<List<paramEntity>> getparam();


    @Headers("Content-Type: application/json")
    @POST("travel/amountStimate")
    Call<Double> amountStimate(@Body valuesTravelPreview travel);


    @Headers("Content-Type: application/json")
    @POST("travel/update")
    Call<InfoTravelEntity> asigned(@Body TravelEntity travel);



}
