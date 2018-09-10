package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 06/01/2017.
 */

public class TravelFilterEntity {

    @Expose
    @SerializedName("filter")
    public TravelFilterBodyEntity filter;

    public TravelFilterEntity() {

    }


    public TravelFilterBodyEntity getFilter() {
        return filter;
    }

    public void setFilter(TravelFilterBodyEntity filter) {
        this.filter = filter;
    }


    public TravelFilterEntity(TravelFilterBodyEntity filter) {
        this.filter = filter;
    }
}
