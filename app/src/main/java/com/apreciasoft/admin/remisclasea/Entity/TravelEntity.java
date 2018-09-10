package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/01/2017.
 */

public class TravelEntity {


    @Expose
    @SerializedName("travel")
    public TravelBodyEntity mTravelBody;

    public TravelEntity(TravelBodyEntity travelBody) {
        mTravelBody = travelBody;
    }

    public TravelEntity() {
    }

    public TravelBodyEntity getTravelBody() {
        return mTravelBody;
    }

    public void setTravelBody(TravelBodyEntity travelBody) {
        mTravelBody = travelBody;
    }
}
