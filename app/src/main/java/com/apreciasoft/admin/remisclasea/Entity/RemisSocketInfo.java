package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by usario on 10/7/2017.
 */

public class RemisSocketInfo {


    @Expose
    @SerializedName("listNotification")
    public List<notification> listNotification;


    @Expose
    @SerializedName("listReservations")
    public List<InfoTravelEntity> listReservations;




    public List<notification> getListNotification() {
        return listNotification;
    }

    public void setListNotification(List<notification> listNotification) {
        this.listNotification = listNotification;
    }

    public List<InfoTravelEntity> getListReservations() {
        return listReservations;
    }

    public void setListReservations(List<InfoTravelEntity> listReservations) {
        this.listReservations = listReservations;
    }
}
