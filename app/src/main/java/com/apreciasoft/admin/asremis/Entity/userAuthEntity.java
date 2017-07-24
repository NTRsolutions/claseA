package com.apreciasoft.admin.asremis.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 02-01-2017.
 */

public class userAuthEntity {

    @Expose
    @SerializedName("user")
    public user user;

    @Expose
    @SerializedName("driver")
    public driver driver;

    @Expose
    @SerializedName("client")
    public client client;

    @Expose
    @SerializedName("param")
    public List<paramEntity> param;


    @Expose
    @SerializedName("vehicleType")
    public List<VehicleType> listVehicleType;

    @Expose
    @SerializedName("currentTravel")
    public InfoTravelEntity currentTravel;

    @Expose
    @SerializedName("instance")
    public String instance;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public com.apreciasoft.admin.asremis.Entity.user getUser() {
        return user;
    }

    public void setUser(com.apreciasoft.admin.asremis.Entity.user user) {
        this.user = user;
    }

    public List<paramEntity> getParam() {
        return param;
    }

    public void setParam(List<paramEntity> param) {
        this.param = param;
    }

    public InfoTravelEntity getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(InfoTravelEntity currentTravel) {
        this.currentTravel = currentTravel;
    }

    public com.apreciasoft.admin.asremis.Entity.driver getDriver() {
        return driver;
    }

    public void setDriver(com.apreciasoft.admin.asremis.Entity.driver driver) {
        this.driver = driver;
    }

    public com.apreciasoft.admin.asremis.Entity.client getClient() {
        return client;
    }

    public void setClient(com.apreciasoft.admin.asremis.Entity.client client) {
        this.client = client;
    }

    public List<VehicleType> getListVehicleType() {
        return listVehicleType;
    }

    public void setListVehicleType(List<VehicleType> listVehicleType) {
        this.listVehicleType = listVehicleType;
    }
}