package com.apreciasoft.mobile.remisclasea.Entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 2/8/2017.
 */

public class RequetClient {


        @Expose
        @SerializedName("client")
        public ClientEntityAdd client;


    public ClientEntityAdd getClient() {
        return client;
    }

    public void setClient(ClientEntityAdd client) {
        this.client = client;
    }

    public RequetClient(ClientEntityAdd client) {
        this.client = client;
    }
}
