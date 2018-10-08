package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 06/01/2017.
 */

public class TravelFilterBodyEntity {


    @Expose
    @SerializedName("idDriverKf")
    public int idDriverKfs;


    @Expose
    @SerializedName("chekin")
    public boolean chekin;

    @Expose
    @SerializedName("longAddresVehicle")
    public String longAddresVehicle;

    @Expose
    @SerializedName("latAddresVehicle")
    public String latAddresVehicle;

    @Expose
    @SerializedName("addresVehicle")
    public String addresVehicle;



    @Expose
    @SerializedName("idVeichleAsigned")
    public int idVeichleAsigned;

    public TravelFilterBodyEntity(int idDriverKfs, boolean chekin, String longAddresVehicle, String latAddresVehicle, String addresVehicle, int idVeichleAsigned) {
        this.idDriverKfs = idDriverKfs;
        this.chekin = chekin;
        this.longAddresVehicle = longAddresVehicle;
        this.latAddresVehicle = latAddresVehicle;
        this.addresVehicle = addresVehicle;
        this.idVeichleAsigned = idVeichleAsigned;
    }

    public boolean isChekin() {
        return chekin;
    }

    public void setChekin(boolean chekin) {
        this.chekin = chekin;
    }

    public String getLongAddresVehicle() {
        return longAddresVehicle;
    }

    public void setLongAddresVehicle(String longAddresVehicle) {
        this.longAddresVehicle = longAddresVehicle;
    }

    public String getLatAddresVehicle() {
        return latAddresVehicle;
    }

    public void setLatAddresVehicle(String latAddresVehicle) {
        this.latAddresVehicle = latAddresVehicle;
    }

    public int getIdVeichleAsigned() {
        return idVeichleAsigned;
    }

    public void setIdVeichleAsigned(int idVeichleAsigned) {
        this.idVeichleAsigned = idVeichleAsigned;
    }

    public int getIdDriverKfs() {
        return idDriverKfs;
    }

    public void setIdDriverKfs(int idDriverKfs) {
        this.idDriverKfs = idDriverKfs;
    }


    public String getAddresVehicle() {
        return addresVehicle;
    }

    public void setAddresVehicle(String addresVehicle) {
        this.addresVehicle = addresVehicle;
    }
}
