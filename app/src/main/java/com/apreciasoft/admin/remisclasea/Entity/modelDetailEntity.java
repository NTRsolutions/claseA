package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 31/7/2017.
 */

public class modelDetailEntity {

    @Expose
    @SerializedName("idVehicleModel")
    public int idVehicleModel;

    @Expose
    @SerializedName("nameVehicleModel")
    public String nameVehicleModel;

    public int getIdVehicleModel() {
        return idVehicleModel;
    }

    public void setIdVehicleModel(int idVehicleModel) {
        this.idVehicleModel = idVehicleModel;
    }

    public String getNameVehicleModel() {
        return nameVehicleModel;
    }

    public void setNameVehicleModel(String nameVehicleModel) {
        this.nameVehicleModel = nameVehicleModel;
    }
}
