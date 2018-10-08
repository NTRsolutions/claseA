package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 20/04/2017.
 */

public class AdvanceEntity {

    @Expose
    @SerializedName("idAdvanceDiver")
    public int idAdvanceDiver;

    @Expose
    @SerializedName("amountAdvance")
    public String amountAdvance;

    @Expose
    @SerializedName("datail")
    public String datail;

    @Expose
    @SerializedName("dateTrasaction")
    public String dateTrasaction;


    public int getIdAdvanceDiver() {
        return idAdvanceDiver;
    }

    public void setIdAdvanceDiver(int idAdvanceDiver) {
        this.idAdvanceDiver = idAdvanceDiver;
    }

    public String getAmountAdvance() {
        return amountAdvance;
    }

    public void setAmountAdvance(String amountAdvance) {
        this.amountAdvance = amountAdvance;
    }

    public String getDatail() {
        return datail;
    }

    public void setDatail(String datail) {
        this.datail = datail;
    }

    public String getDateTrasaction() {
        return dateTrasaction;
    }

    public void setDateTrasaction(String dateTrasaction) {
        this.dateTrasaction = dateTrasaction;
    }
}
