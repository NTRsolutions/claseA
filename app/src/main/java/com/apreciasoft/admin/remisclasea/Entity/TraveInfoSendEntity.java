package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/1/2017.
 */

public class TraveInfoSendEntity {

    @Expose
    @SerializedName("travel")
    public TravelLocationEntity travel;


    public TraveInfoSendEntity(TravelLocationEntity travel) {
        this.travel = travel;
    }

    public TravelLocationEntity getTravel() {
        return travel;
    }

    public void setTravel(TravelLocationEntity travel) {
        this.travel = travel;
    }
}
