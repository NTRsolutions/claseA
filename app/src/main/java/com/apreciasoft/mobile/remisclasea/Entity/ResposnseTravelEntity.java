package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/01/2017.
 */

public class ResposnseTravelEntity {

    @Expose
    @SerializedName("response")
    public TravelBodyEntity response;


    public TravelBodyEntity getResponse() {
        return response;
    }

    public void setResponse(TravelBodyEntity response) {
        this.response = response;
    }
}
