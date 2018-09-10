package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

/**
 * Created by Admin on 20/1/2017.
 */

@Nullable
public class paramEntity {


    @Expose
    @SerializedName("idParam")
    public int idParam;

    @Expose
    @SerializedName("value")
    public String value;

    @Expose
    @SerializedName("description")
    public String description;


    public int getIdParam() {
        return idParam;
    }

    public void setIdParam(int idParam) {
        this.idParam = idParam;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
