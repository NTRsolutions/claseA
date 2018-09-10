package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 25/9/17.
 */

public class costCenterCompany {

    @Expose
    @SerializedName("costCenter")
    public String costCenter;


    @Expose
    @SerializedName("idCostCenter")
    public int idCostCenter;

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public int getIdCostCenter() {
        return idCostCenter;
    }

    public void setIdCostCenter(int idCostCenter) {
        this.idCostCenter = idCostCenter;
    }
}
