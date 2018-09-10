package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usario on 2/8/2017.
 */

public class ClientEntityAdd {


    @Expose
    @SerializedName("dniClient")
    public String dniClient;

    @Expose
    @SerializedName("phoneClient")
    public String phoneClient;

    @Expose
    @SerializedName("firtNameClient")
    public String firtNameClient;

    @Expose
    @SerializedName("lastNameClient")
    public String lastNameClient;

    @Expose
    @SerializedName("mailClient")
    public String mailClient;

    @Expose
    @SerializedName("passClient")
    public String passClient;

    @Expose
    @SerializedName("idTypeClient")
    public int idTypeClient;


    @Expose
    @SerializedName("idCompanyAcount")
    public int idCompanyAcount;


    @Expose
    @SerializedName("phone")
    public String phone;

    @Expose
    @SerializedName("idCostCenter")
    public int idCostCenter;



    @Expose
    @SerializedName("idCompanyKf")
    public int idCompanyKf;


    public int getIdCompanyKf() {
        return idCompanyKf;
    }

    public void setIdCompanyKf(int idCompanyKf) {
        this.idCompanyKf = idCompanyKf;
    }

    public int getIdCompanyAcount() {
        return idCompanyAcount;
    }

    public void setIdCompanyAcount(int idCompanyAcount) {
        this.idCompanyAcount = idCompanyAcount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdCostCenter() {
        return idCostCenter;
    }

    public void setIdCostCenter(int idCostCenter) {
        this.idCostCenter = idCostCenter;
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

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public String getPassClient() {
        return passClient;
    }

    public void setPassClient(String passClient) {
        this.passClient = passClient;
    }

    public int getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(int idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public ClientEntityAdd(String firtNameClient, String lastNameClient, String mailClient, String passClient, int idTypeClient, int idCompanyAcount, String phone,
                           int idCostCenter,int idCompanyKf) {
        this.firtNameClient = firtNameClient;
        this.lastNameClient = lastNameClient;
        this.mailClient = mailClient;
        this.passClient = passClient;
        this.idTypeClient = idTypeClient;
        this.idCompanyAcount = idCompanyAcount;
        this.phone = phone;
        this.idCostCenter = idCostCenter;
        this.idCompanyKf = idCompanyKf;
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
}
