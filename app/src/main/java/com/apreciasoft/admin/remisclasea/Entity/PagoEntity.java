package com.apreciasoft.admin.asremis.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jorge on 10/3/18.
 */

public class PagoEntity {


    @Expose
    @SerializedName("CLIENT_ID")
    public String CLIENT_ID;

    @Expose
    @SerializedName("CLIENT_SECRET")
    public String CLIENT_SECRET;

    @Expose
    @SerializedName("currency_id")
    public String currency_id;

    @Expose
    @SerializedName("totalFinal")
    public double totalFinal;

    public PagoEntity(String CLIENT_ID, String CLIENT_SECRET, String currency_id, double totalFinal) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.currency_id = currency_id;
        this.totalFinal = totalFinal;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    public String getCLIENT_SECRET() {
        return CLIENT_SECRET;
    }

    public void setCLIENT_SECRET(String CLIENT_SECRET) {
        this.CLIENT_SECRET = CLIENT_SECRET;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }
}
