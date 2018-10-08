package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge on 29/4/2017.
 */

public class client {

    @Expose
    @SerializedName("idClient")
    public int idClient;

    @Expose
    @SerializedName("firtNameClient")
    public String firtNameClient;

    @Expose
    @SerializedName("lastNameClient")
    public String lastNameClient;

    @Expose
    @SerializedName("dniClient")
    public String dniClient;


    @Expose
    @SerializedName("phoneClient")
    public String phoneClient;

    @Expose
    @SerializedName("mailClient")
    public String mailClient;


    @Expose
    @SerializedName("idUser")
    public int idUser;

    public client(int idClient, String firtNameClient, String lastNameClient, String dniClient, String phoneClient, String mailClient, int idUser) {
        this.idClient = idClient;
        this.firtNameClient = firtNameClient;
        this.lastNameClient = lastNameClient;
        this.dniClient = dniClient;
        this.phoneClient = phoneClient;
        this.mailClient = mailClient;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirtNameClient() {
        return firtNameClient;
    }

    public void setFirtNameClient(String firtNameClient) {
        this.firtNameClient = firtNameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getDniClient() {
        return dniClient;
    }

    public void setDniClient(String dniClient) {
        this.dniClient = dniClient;
    }

    public String getPhoneClient() {
        return phoneClient;
    }

    public void setPhoneClient(String phoneClient) {
        this.phoneClient = phoneClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }
}
