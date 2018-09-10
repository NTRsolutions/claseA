package com.apreciasoft.admin.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.loopj.android.http.RequestParams;

/**
 * Created by Admin on 02-01-2017.
 */

public class login extends RequestParams {

    @Expose
    @SerializedName("user")
    public user mUser;

    public user getUser() {
        return mUser;
    }

    public void setUser(user user) {
        mUser = user;
    }

    public login(user user) {
        this.mUser = user;
    }


    public login() {
    }
}
