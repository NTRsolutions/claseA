package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jorge on 9/3/18.
 */

public class ResponsePHP {


    @Expose
    @SerializedName("url")
    public String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
