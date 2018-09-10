package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jorge on 23/11/17.
 */

public class clienteFull {

    @Expose
    @SerializedName("client")
    public client client;

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }
}
