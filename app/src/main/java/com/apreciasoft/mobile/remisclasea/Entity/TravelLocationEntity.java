package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/1/2017.
 */

public class TravelLocationEntity {

    @Expose
    @SerializedName("idUser")
    public int idUser;

    @Expose
    @SerializedName("idTravelKf")
    public int idTravelKf;

    @Expose
    @SerializedName("idDriverKf")
    public int idDriverKf;

    @Expose
    @SerializedName("idVeichleAsigned")
    public int idVeichleAsigned;

    @Expose
    @SerializedName("idClientKf")
    public int idClientKf;


    @Expose
    @SerializedName("distanceSave")
    public Double distanceSave;





    @Expose
    @SerializedName("totalAmount")
    public Double totalAmount;


    @Expose
    @SerializedName("location")
    public String location;

    @Expose
    @SerializedName("longLocation")
    public String longLocation;

    @Expose
    @SerializedName("latLocation")
    public String latLocation;


    @Expose
    @SerializedName("distanceGps")
    public Double distanceGps;



    @Expose
    @SerializedName("distanceGpsReturn")
    public Double distanceGpsReturn;



    @Expose
    @SerializedName("distanceGpsLabel")
    public String distanceGpsLabel;


    @Expose
    @SerializedName("amounttoll")
    public Double amounttoll;

    @Expose
    @SerializedName("amountParking")
    public Double amountParking;

    @Expose
    @SerializedName("amountTiemeSlepp")
    public Double amountTiemeSlepp;


    @Expose
    @SerializedName("timeSleppGps")
    public String timeSleppGps;

    @Expose
    @SerializedName("idPaymentFormKf")
    public int idPaymentFormKf;


    @Expose
    @SerializedName("mp_jsonPaymentCard")
    public String mp_jsonPaymentCard;

    @Expose
    @SerializedName("mp_paymentMethodId")
    public String mp_paymentMethodId;

    @Expose
    @SerializedName("mp_paymentTypeId")
    public String mp_paymentTypeId;

    @Expose
    @SerializedName("mp_paymentstatus")
    public String mp_paymentstatus;

    @Expose
    @SerializedName("amountFleet")
    public Double amountFleet;



    public TravelLocationEntity(int idUser,int idTravelKf, String location, String longLocation,
                                String latLocation,int idDriverKf,int idVeichleAsigned,int idClientKf,double distanceSave,double distanceGpsReturn

    ) {
        this.idUser = idUser;
        this.idTravelKf = idTravelKf;
        this.location = location;
        this.longLocation = longLocation;
        this.latLocation = latLocation;
        this.idDriverKf = idDriverKf;
        this.idVeichleAsigned = idVeichleAsigned;
        this.idClientKf = idClientKf;
        this.distanceSave = distanceSave;
        this.distanceGpsReturn = distanceGpsReturn;


    }

    public Double getDistanceGpsReturn() {
        return distanceGpsReturn;
    }

    public void setDistanceGpsReturn(Double distanceGpsReturn) {
        this.distanceGpsReturn = distanceGpsReturn;
    }

    public Double getAmounttoll() {
        return amounttoll;
    }

    public void setAmounttoll(Double amounttoll) {
        this.amounttoll = amounttoll;
    }

    public Double getAmountParking() {
        return amountParking;
    }

    public void setAmountParking(Double amountParking) {
        this.amountParking = amountParking;
    }

    public Double getAmountTiemeSlepp() {
        return amountTiemeSlepp;
    }

    public void setAmountTiemeSlepp(Double amountTiemeSlepp) {
        this.amountTiemeSlepp = amountTiemeSlepp;
    }

    public String getTimeSleppGps() {
        return timeSleppGps;
    }

    public void setTimeSleppGps(String timeSleppGps) {
        this.timeSleppGps = timeSleppGps;
    }

    public int getIdDriverKf() {
        return idDriverKf;
    }

    public void setIdDriverKf(int idDriverKf) {
        this.idDriverKf = idDriverKf;
    }


    public int getIdVeichleAsigned() {
        return idVeichleAsigned;
    }

    public void setIdVeichleAsigned(int idVeichleAsigned) {
        this.idVeichleAsigned = idVeichleAsigned;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    // FINALIZAR VIAJE //
    public TravelLocationEntity(int idTravelKf, Double totalAmount, Double distanceGps, String distanceGpsLabel,
                                String location, String longLocation, String latLocation,
                                double amounttoll,double amountParking,double amountTiemeSlepp,String timeSleppGps,int idPaymentFormKf,
                                String mp_jsonPaymentCard,String mp_paymentMethodId,String mp_paymentTypeId,String mp_paymentstatus,double amountFleet,double distanceGpsReturn) {
        this.idTravelKf = idTravelKf;
        this.totalAmount = totalAmount;
        this.distanceGps = distanceGps;
        this.distanceGpsLabel = distanceGpsLabel;
        this.location = location;
        this.longLocation = longLocation;
        this.latLocation = latLocation;

        this.amounttoll = amounttoll;
        this.amountParking = amountParking;
        this.amountTiemeSlepp = amountTiemeSlepp;
        this.timeSleppGps = timeSleppGps;
        this.idPaymentFormKf = idPaymentFormKf;


        this.mp_jsonPaymentCard = mp_jsonPaymentCard;
        this.mp_paymentMethodId = mp_paymentMethodId;
        this.mp_paymentTypeId = mp_paymentTypeId;
        this.mp_paymentstatus = mp_paymentstatus;

        this.amountFleet =amountFleet;

        this.distanceGpsReturn = distanceGpsReturn;



    }

    public int getIdTravelKf() {
        return idTravelKf;
    }

    public void setIdTravelKf(int idTravelKf) {
        this.idTravelKf = idTravelKf;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongLocation() {
        return longLocation;
    }

    public void setLongLocation(String longLocation) {
        this.longLocation = longLocation;
    }

    public String getLatLocation() {
        return latLocation;
    }

    public void setLatLocation(String latLocation) {
        this.latLocation = latLocation;
    }




    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Double getDistanceGps() {
        return distanceGps;
    }

    public void setDistanceGps(Double distanceGps) {
        this.distanceGps = distanceGps;
    }

    public String getDistanceGpsLabel() {
        return distanceGpsLabel;
    }

    public void setDistanceGpsLabel(String distanceGpsLabel) {
        this.distanceGpsLabel = distanceGpsLabel;
    }

    public int getIdClientKf() {
        return idClientKf;
    }

    public void setIdClientKf(int idClientKf) {
        this.idClientKf = idClientKf;
    }

    public Double getDistanceSave() {
        return distanceSave;
    }

    public void setDistanceSave(Double distanceSave) {
        this.distanceSave = distanceSave;
    }

    public int getIdPaymentFormKf() {
        return idPaymentFormKf;
    }

    public void setIdPaymentFormKf(int idPaymentFormKf) {
        this.idPaymentFormKf = idPaymentFormKf;
    }

    public String getMp_jsonPaymentCard() {
        return mp_jsonPaymentCard;
    }

    public void setMp_jsonPaymentCard(String mp_jsonPaymentCard) {
        this.mp_jsonPaymentCard = mp_jsonPaymentCard;
    }

    public String getMp_paymentMethodId() {
        return mp_paymentMethodId;
    }

    public void setMp_paymentMethodId(String mp_paymentMethodId) {
        this.mp_paymentMethodId = mp_paymentMethodId;
    }

    public String getMp_paymentTypeId() {
        return mp_paymentTypeId;
    }

    public void setMp_paymentTypeId(String mp_paymentTypeId) {
        this.mp_paymentTypeId = mp_paymentTypeId;
    }

    public String getMp_paymentstatus() {
        return mp_paymentstatus;
    }

    public void setMp_paymentstatus(String mp_paymentstatus) {
        this.mp_paymentstatus = mp_paymentstatus;
    }

    public Double getAmountFleet() {
        return amountFleet;
    }

    public void setAmountFleet(Double amountFleet) {
        this.amountFleet = amountFleet;
    }
}
