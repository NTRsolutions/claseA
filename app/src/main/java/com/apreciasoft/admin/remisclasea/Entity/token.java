package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 13/02/2017.
 */

public class token {

    @Expose
    @SerializedName("token")
    public tokenFull token;


    public tokenFull getToken() {
        return token;
    }

    public void setToken(tokenFull token) {
        this.token = token;
    }
}
