package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 29/03/2017.
 */

public class VehicleType {

    @Expose
    @SerializedName("idVehicleType")
    public int idVehicleType;

    @Expose
    @SerializedName("vehiclenType")
    public String vehiclenType;


    public int getIdVehicleType() {
        return idVehicleType;
    }

    public void setIdVehicleType(int idVehicleType) {
        this.idVehicleType = idVehicleType;
    }

    public String getVehiclenType() {
        return vehiclenType;
    }

    public void setVehiclenType(String vehiclenType) {
        this.vehiclenType = vehiclenType;
    }

    public VehicleType(int idVehicleType, String vehiclenType) {
        this.idVehicleType = idVehicleType;
        this.vehiclenType = vehiclenType;
    }
}
