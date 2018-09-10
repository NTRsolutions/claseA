package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 19/04/2017.
 */

public class LiquidationEntity {

    @Expose
    @SerializedName("idHeader")
    public int idHeader;

    @Expose
    @SerializedName("dateTrasaction")
    public String dateTrasaction;

    @Expose
    @SerializedName("totalLiquidation")
    public String totalLiquidation;

    @Expose
    @SerializedName("idTipeOperation")
    public int idTipeOperation;

    @Expose
    @SerializedName("tipeOperationDes")
    public String tipeOperationDes;

    @Expose
    @SerializedName("codeCardx")
    public String codeCardx;

    @Expose
    @SerializedName("isProcesPayment")
    public int isProcesPayment;


    public int getIsProcesPayment() {
        return isProcesPayment;
    }

    public void setIsProcesPayment(int isProcesPayment) {
        this.isProcesPayment = isProcesPayment;
    }

    public String getCodeCardx() {
        return codeCardx;
    }

    public void setCodeCardx(String codeCardx) {
        this.codeCardx = codeCardx;
    }

    public int getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(int idHeader) {
        this.idHeader = idHeader;
    }

    public String getDateTrasaction() {
        return dateTrasaction;
    }

    public void setDateTrasaction(String dateTrasaction) {
        this.dateTrasaction = dateTrasaction;
    }

    public String getTotalLiquidation() {
        return totalLiquidation;
    }

    public void setTotalLiquidation(String totalLiquidation) {
        this.totalLiquidation = totalLiquidation;
    }

    public int getIdTipeOperation() {
        return idTipeOperation;
    }

    public void setIdTipeOperation(int idTipeOperation) {
        this.idTipeOperation = idTipeOperation;
    }

    public String getTipeOperationDes() {
        return tipeOperationDes;
    }

    public void setTipeOperationDes(String tipeOperationDes) {
        this.tipeOperationDes = tipeOperationDes;
    }
}
