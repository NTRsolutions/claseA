package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jorge on 30/1/18.
 */

public class reasonEntity {

    @Expose
    @SerializedName("reason")
    public List<reason> reason;

    public List<reason> getReason() {
        return reason;
    }

    public void setReason(List<reason> reason) {
        this.reason = reason;
    }
}
