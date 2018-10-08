package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 25/9/17.
 */

public class acountCompany {

    @Expose
    @SerializedName("idCompanyAcount")
    public int idCompanyAcount;


    @Expose
    @SerializedName("nrAcount")
    public String nrAcount;

    public int getIdCompanyAcount() {
        return idCompanyAcount;
    }

    public void setIdCompanyAcount(int idCompanyAcount) {
        this.idCompanyAcount = idCompanyAcount;
    }

    public String getNrAcount() {
        return nrAcount;
    }

    public void setNrAcount(String nrAcount) {
        this.nrAcount = nrAcount;
    }
}
