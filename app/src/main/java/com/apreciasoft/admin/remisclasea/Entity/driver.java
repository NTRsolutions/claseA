package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 27/4/2017.
 */

public class driver {

    @Expose
    @SerializedName("idDriver")
    public int idDriver;

    @Expose
    @SerializedName("fisrtNameDriver")
    public String fisrtNameDriver;

    @Expose
    @SerializedName("lastNameDriver")
    public String lastNameDriver;

    @Expose
    @SerializedName("dniDriver")
    public String dniDriver;

    @Expose
    @SerializedName("phoneNumberDriver")
    public String phoneNumberDriver;

    @Expose
    @SerializedName("emailDriver")
    public String emailDriver;

    @Expose
    @SerializedName("nrDriver")
    public String nrDriver;

    @Expose
    @SerializedName("idUser")
    public int idUser;

    @Expose
    @SerializedName("idStatusDriverTravelKf")
    public int idStatusDriverTravelKf;






    public driver(int idDriver, String fisrtNameDriver, String dniDriver, String phoneNumberDriver, String emailDriver, int idUser) {
        this.idDriver = idDriver;
        this.fisrtNameDriver = fisrtNameDriver;
        this.dniDriver = dniDriver;
        this.phoneNumberDriver = phoneNumberDriver;
        this.emailDriver = emailDriver;
        this.idUser = idUser;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getFisrtNameDriver() {
        return fisrtNameDriver;
    }

    public void setFisrtNameDriver(String fisrtNameDriver) {
        this.fisrtNameDriver = fisrtNameDriver;
    }

    public String getLastNameDriver() {
        return lastNameDriver;
    }

    public void setLastNameDriver(String lastNameDriver) {
        this.lastNameDriver = lastNameDriver;
    }

    public String getDniDriver() {
        return dniDriver;
    }

    public void setDniDriver(String dniDriver) {
        this.dniDriver = dniDriver;
    }

    public String getPhoneNumberDriver() {
        return phoneNumberDriver;
    }

    public void setPhoneNumberDriver(String phoneNumberDriver) {
        this.phoneNumberDriver = phoneNumberDriver;
    }

    public String getEmailDriver() {
        return emailDriver;
    }

    public void setEmailDriver(String emailDriver) {
        this.emailDriver = emailDriver;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdStatusDriverTravelKf() {
        return idStatusDriverTravelKf;
    }

    public void setIdStatusDriverTravelKf(int idStatusDriverTravelKf) {
        this.idStatusDriverTravelKf = idStatusDriverTravelKf;
    }

    public String getNrDriver() {
        return nrDriver;
    }

    public void setNrDriver(String nrDriver) {
        this.nrDriver = nrDriver;
    }
}
