package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 10/6/2017.
 */

public class MultiDestination {

    @Expose
    @SerializedName("nameDesMultiDestination")
    public String nameDesMultiDestination;

    @Expose
    @SerializedName("longMultiDestination")
    public String longMultiDestination;

    @Expose
    @SerializedName("latMultiDestination")
    public String latMultiDestination;


    public String getNameDesMultiDestination() {
        return nameDesMultiDestination;
    }

    public void setNameDesMultiDestination(String nameDesMultiDestination) {
        this.nameDesMultiDestination = nameDesMultiDestination;
    }

    public String getLongMultiDestination() {
        return longMultiDestination;
    }

    public void setLongMultiDestination(String longMultiDestination) {
        this.longMultiDestination = longMultiDestination;
    }

    public String getLatMultiDestination() {
        return latMultiDestination;
    }

    public void setLatMultiDestination(String latMultiDestination) {
        this.latMultiDestination = latMultiDestination;
    }
}
