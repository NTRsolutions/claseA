package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/1/2017.
 */

public class userFull  {


    @Expose
    @SerializedName("response")
    public userAuthEntity response;

    public userAuthEntity getResponse() {
        return response;
    }

    public void setResponse(userAuthEntity response) {
        this.response = response;
    }
}
