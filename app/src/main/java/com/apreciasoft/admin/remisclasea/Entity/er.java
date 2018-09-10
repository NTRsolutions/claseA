package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 06/01/2017.
 */

public class er {
    @Expose
    @SerializedName("error")
    public String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
