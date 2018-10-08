package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 1/8/2017.
 */

public class fleet {


    @Expose
    @SerializedName("idVeichleBrandAsigned")
    public int idVeichleBrandAsigned;


    @Expose
    @SerializedName("idVehicleModelAsigned")
    public int idVehicleModelAsigned;

    @Expose
    @SerializedName("idVehiclenTypeAsigned")
    public int idVehiclenTypeAsigned;


    @Expose
    @SerializedName("domain")
    public String domain;


    public fleet(int idVeichleBrandAsigned, int idVehicleModelAsigned, int idVehiclenTypeAsigned, String domain) {
        this.idVeichleBrandAsigned = idVeichleBrandAsigned;
        this.idVehicleModelAsigned = idVehicleModelAsigned;
        this.idVehiclenTypeAsigned = idVehiclenTypeAsigned;
        this.domain = domain;
    }

    public int getIdVeichleBrandAsigned() {
        return idVeichleBrandAsigned;
    }

    public void setIdVeichleBrandAsigned(int idVeichleBrandAsigned) {
        this.idVeichleBrandAsigned = idVeichleBrandAsigned;
    }

    public int getIdVehicleModelAsigned() {
        return idVehicleModelAsigned;
    }

    public void setIdVehicleModelAsigned(int idVehicleModelAsigned) {
        this.idVehicleModelAsigned = idVehicleModelAsigned;
    }

    public int getIdVehiclenTypeAsigned() {
        return idVehiclenTypeAsigned;
    }

    public void setIdVehiclenTypeAsigned(int idVehiclenTypeAsigned) {
        this.idVehiclenTypeAsigned = idVehiclenTypeAsigned;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
