package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 29/9/17.
 */

public class Company {



        @Expose
        @SerializedName("idClientKf")
        public int idClientKf;

        @Expose
        @SerializedName("nameClientCompany")
        public String nameClientCompany;

    public int getIdClientKf() {
        return idClientKf;
    }

    public void setIdClientKf(int idClientKf) {
        this.idClientKf = idClientKf;
    }

    public String getNameClientCompany() {
        return nameClientCompany;
    }

    public void setNameClientCompany(String nameClientCompany) {
        this.nameClientCompany = nameClientCompany;
    }
}
