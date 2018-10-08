package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/01/2017.
 */

public class DestinationEntity {

    @Expose
    @SerializedName("latDestination")
    public String latDestination;

    @Expose
    @SerializedName("lonDestination")
    public String lonDestination;

    @Expose
    @SerializedName("nameDestination")
    public String nameDestination;

    public DestinationEntity(String latDestination, String lonDestination, String nameDestination) {
        this.latDestination = latDestination;
        this.lonDestination = lonDestination;
        this.nameDestination = nameDestination;
    }

    public String getLatDestination() {
        return latDestination;
    }

    public void setLatDestination(String latDestination) {
        this.latDestination = latDestination;
    }

    public String getLonDestination() {
        return lonDestination;
    }

    public void setLonDestination(String lonDestination) {
        this.lonDestination = lonDestination;
    }

    public String getNameDestination() {
        return nameDestination;
    }

    public void setNameDestination(String nameDestination) {
        this.nameDestination = nameDestination;
    }
}
