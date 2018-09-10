package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/01/2017.
 */

public class OriginEntity {

    @Expose
    @SerializedName("latOrigin")
    public String latOrigin;

    @Expose
    @SerializedName("lonOrigin")
    public String lonOrigin;

    @Expose
    @SerializedName("nameOrigin")
    public String nameOrigin;


    public OriginEntity(String latOrigin, String lonOrigin, String nameOrigin) {
        this.latOrigin = latOrigin;
        this.lonOrigin = lonOrigin;
        this.nameOrigin = nameOrigin;
    }

    public String getLatOrigin() {
        return latOrigin;
    }

    public void setLatOrigin(String latOrigin) {
        this.latOrigin = latOrigin;
    }

    public String getLonOrigin() {
        return lonOrigin;
    }

    public void setLonOrigin(String lonOrigin) {
        this.lonOrigin = lonOrigin;
    }

    public String getNameOrigin() {
        return nameOrigin;
    }

    public void setNameOrigin(String nameOrigin) {
        this.nameOrigin = nameOrigin;
    }
}
