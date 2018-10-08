package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class reporte {

    @Expose
    @SerializedName("userId")
    public int userId;

    @Expose
    @SerializedName("fullName")
    public String fullName;

    @Expose
    @SerializedName("correo")
    public String correo;

    @Expose
    @SerializedName("correo2")
    public String correo2;

    @Expose
    @SerializedName("reason")
    public String reason;

    @Expose
    @SerializedName("company")
    public String company;


    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("isTravelSendMovil")
    public int isTravelSendMovil;

    public reporte(int userId, String fullName, String correo, String correo2, String reason, String company, String message, int isTravelSendMovil) {
        this.userId = userId;
        this.fullName = fullName;
        this.correo = correo;
        this.correo2 = correo2;
        this.reason = reason;
        this.company = company;
        this.message = message;
        this.isTravelSendMovil = isTravelSendMovil;
    }

    //obtener id de usuario
    public int getIdUser() {
        return userId;
    }
    public void setIdUser(int userId) {
        this.userId = userId;
    }

    //obtener nombre completo
    public String getfullName() {
        return fullName;
    }
    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    //obtener correo
    public String getcorreo() {
        return correo;
    }
    public void setcorreo(String correo) {
        this.correo = correo;
    }

    //obtener correo 2
    public String getcorreo2() {
        return correo2;
    }
    public void setcorreo2(String correo2) {
        this.correo2 = correo2;
    }

    //obtener razon
    public String getreason() {
        return reason;
    }
    public void setreason(String reason) {
        this.reason = reason;
    }

    //obtener company
    public String getcompany() {
        return company;
    }
    public void setcompany(String company) {
        this.company = company;
    }

    //obtener message
    public String getmessage() {
        return message;
    }
    public void setmessage(String message) {
        this.message = message;
    }

    //obtener message
    public int getisTravelSendMovil() {
        return isTravelSendMovil;
    }
    public void setmessage(int isTravelSendMovil) {
        this.isTravelSendMovil = isTravelSendMovil;
    }
}
