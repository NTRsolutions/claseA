package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 13/02/2017.
 */

public class tokenFull {

    @Expose
    @SerializedName("tokenFB")
    public String tokenFB;

    @Expose
    @SerializedName("idUser")
    public int idUser;

    @Expose
    @SerializedName("idDriver")
    public int idDriver;

    @Expose
    @SerializedName("latVersionApp")
    public String latVersionApp;

    @Expose
    @SerializedName("idSocketMap")
    public String idSocketMap;

    public tokenFull(int idUser, String idSocketMap) {
        this.idUser = idUser;
        this.idSocketMap = idSocketMap;
    }

    public tokenFull(String tokenFB, int idUser, int idDriver, String latVersionApp) {
        this.tokenFB = tokenFB;
        this.idUser = idUser;
        this.idDriver = idDriver;
        this.latVersionApp = latVersionApp;
    }


    public String getIdSocketMap() {
        return idSocketMap;
    }

    public void setIdSocketMap(String idSocketMap) {
        this.idSocketMap = idSocketMap;
    }

    public String getLatVersionApp() {
        return latVersionApp;
    }

    public void setLatVersionApp(String latVersionApp) {
        this.latVersionApp = latVersionApp;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getTokenFB() {
        return tokenFB;
    }

    public void setTokenFB(String tokenFB) {
        this.tokenFB = tokenFB;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
