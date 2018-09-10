package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Nullable;

/**
 * Created by Jorge on 28/10/17.
 */


@Nullable
public  class BeneficioEntity implements Serializable {


    @Expose
    @SerializedName("idBenefitsPerKm")
    public int idBenefitsPerKm;

    @Expose
    @SerializedName("BenefitsFromKm")
    public String BenefitsFromKm;

    @Expose
    @SerializedName("BenefitsToKm")
    public String BenefitsToKm;

    @Expose
    @SerializedName("BenefitsPreceKm")
    public String BenefitsPreceKm;



    @Expose
    @SerializedName("idBenefitKmFk")
    public int idBenefitKmFk;

    @Expose
    @SerializedName("benefitPreceReturnKm")
    public String benefitPreceReturnKm;

    public String getBenefitsFromKm() {
        return BenefitsFromKm;
    }

    public void setBenefitsFromKm(String benefitsFromKm) {
        BenefitsFromKm = benefitsFromKm;
    }

    public String getBenefitsToKm() {
        return BenefitsToKm;
    }

    public void setBenefitsToKm(String benefitsToKm) {
        BenefitsToKm = benefitsToKm;
    }

    public String getBenefitsPreceKm() {
        return BenefitsPreceKm;
    }

    public void setBenefitsPreceKm(String benefitsPreceKm) {
        BenefitsPreceKm = benefitsPreceKm;
    }

    public int getIdBenefitsPerKm() {
        return idBenefitsPerKm;
    }

    public void setIdBenefitsPerKm(int idBenefitsPerKm) {
        this.idBenefitsPerKm = idBenefitsPerKm;
    }

    public int getIdBenefitKmFk() {
        return idBenefitKmFk;
    }

    public void setIdBenefitKmFk(int idBenefitKmFk) {
        this.idBenefitKmFk = idBenefitKmFk;
    }

    public String getBenefitPreceReturnKm() {
        return benefitPreceReturnKm;
    }

    public void setBenefitPreceReturnKm(String benefitPreceReturnKm) {
        this.benefitPreceReturnKm = benefitPreceReturnKm;
    }
}