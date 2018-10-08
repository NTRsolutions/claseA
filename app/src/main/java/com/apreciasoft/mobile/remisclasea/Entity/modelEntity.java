package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 31/7/2017.
 */

public class modelEntity {


    @Expose
    @SerializedName("idVehicleBrand")
    public int idVehicleBrand;

    @Expose
    @SerializedName("nameVehicleBrand")
    public String nameVehicleBrand;

    public int getIdVehicleBrand() {
        return idVehicleBrand;
    }

    public void setIdVehicleBrand(int idVehicleBrand) {
        this.idVehicleBrand = idVehicleBrand;
    }

    public String getNameVehicleBrand() {
        return nameVehicleBrand;
    }

    public void setNameVehicleBrand(String nameVehicleBrand) {
        this.nameVehicleBrand = nameVehicleBrand;
    }
}
