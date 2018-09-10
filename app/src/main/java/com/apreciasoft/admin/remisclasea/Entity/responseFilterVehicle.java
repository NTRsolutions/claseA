package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by usario on 31/7/2017.
 */

public class responseFilterVehicle {

    @Expose
    @SerializedName("listModel")
    public List<modelDetailEntity> listModel;

    public List<modelDetailEntity> getListModel() {
        return listModel;
    }

    public void setListModel(List<modelDetailEntity> listModel) {
        this.listModel = listModel;
    }
}
