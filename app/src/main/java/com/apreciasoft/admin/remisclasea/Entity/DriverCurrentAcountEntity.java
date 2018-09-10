package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jorge gutierrez on 13/04/2017.
 */

public class DriverCurrentAcountEntity {

    @Expose
    @SerializedName("liquidation")
    public List<LiquidationEntity> liquidation;

    @Expose
    @SerializedName("advance")
    public List<LiquidationEntity> advance;

    @Expose
    @SerializedName("pay")
    public List<LiquidationEntity> pay;

    @Expose
    @SerializedName("total")
    public total total;

    public List<LiquidationEntity> getPay() {
        return pay;
    }

    public void setPay(List<LiquidationEntity> pay) {
        this.pay = pay;
    }


    public DriverCurrentAcountEntity(List<LiquidationEntity> liquidation, List<LiquidationEntity> advance, List<LiquidationEntity> pay, total total) {
        this.liquidation = liquidation;
        this.advance = advance;
        this.pay = pay;
        this.total = total;
    }

    public List<LiquidationEntity> getAdvance() {
        return advance;
    }

    public void setAdvance(List<LiquidationEntity> advance) {
        this.advance = advance;
    }

    public total getTotal() {
        return total;
    }

    public void setTotal(total total) {
        this.total = total;
    }

    public List<LiquidationEntity> getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(List<LiquidationEntity> liquidation) {
        this.liquidation = liquidation;
    }
}
