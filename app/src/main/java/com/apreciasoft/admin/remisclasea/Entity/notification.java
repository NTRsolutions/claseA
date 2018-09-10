package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorge gutierrez on 18/04/2017.
 */

public class notification {

    @Expose
    @SerializedName("idNotification")
    public int idNotification;

    @Expose
    @SerializedName("idType")
    public int idType;

    @Expose
    @SerializedName("isRead")
    public int isRead;

    @Expose
    @SerializedName("subTitle")
    public String subTitle;

    @Expose
    @SerializedName("title")
    public String title;

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
