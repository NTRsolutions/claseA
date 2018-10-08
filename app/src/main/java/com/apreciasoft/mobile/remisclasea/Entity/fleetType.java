package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 31/7/2017.
 */

public class fleetType {

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
}
