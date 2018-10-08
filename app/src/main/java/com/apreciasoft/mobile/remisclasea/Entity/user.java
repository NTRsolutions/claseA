package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 01-01-2017.
 */

public class user {

    @Expose
    @SerializedName("userName")
    String mUserName;

    @Expose
    @SerializedName("idVeichleAsigned")
    int mIdVeichleAsigned;



    @Expose
    @SerializedName("userPass")
    String mUserPass;


    @Expose
    @SerializedName("idUser")
    int mIdUser;

    @Expose
    @SerializedName("userNameUser")
    String mUserNameUser;

    @Expose
    @SerializedName("firstNameUser")
    String mFirstNameUser;

    @Expose
    @SerializedName("lastNameUser")
    String mLastNameUser;

    @Expose
    @SerializedName("emailUser")
    String mEmailUser;

    @Expose
    @SerializedName("idProfileUser")
    int mIdProfileUser;

    @Expose
    @SerializedName("idStatusUser")
    String midStatusUser;


    @Expose
    @SerializedName("idClient")
    int mIdClient;


    @Expose
    @SerializedName("idDriver")
    int mIdDriver;

    @Expose
    @SerializedName("idTypeAuth")
    int idTypeAuth;

    @Expose
    @SerializedName("idResourceSocket")
    String idResourceSocket;

    public String getIdResourceSocket() {
        return idResourceSocket;
    }

    public void setIdResourceSocket(String idResourceSocket) {
        this.idResourceSocket = idResourceSocket;
    }

    public int getIdTypeAuth() {
        return idTypeAuth;
    }

    public void setIdTypeAuth(int idTypeAuth) {
        this.idTypeAuth = idTypeAuth;
    }

    public int getIdClient() {
        return mIdClient;
    }

    public void setIdClient(int idClient) {
        mIdClient = idClient;
    }

    public int getIdDriver() {
        return mIdDriver;
    }

    public void setIdDriver(int idDriver) {
        mIdDriver = idDriver;
    }

    public user(String userName, String userPass ,int idTypeAuth ) {
        this.mUserName = userName;
        this.mUserPass = userPass;
        this.idTypeAuth = idTypeAuth;
    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPass() {
        return mUserPass;
    }

    public void setUserPass(String userPass) {
        mUserPass = userPass;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int idUser) {
        mIdUser = idUser;
    }

    public String getUserNameUser() {
        return mUserNameUser;
    }

    public void setUserNameUser(String userNameUser) {
        mUserNameUser = userNameUser;
    }

    public String getFirstNameUser() {
        return mFirstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        mFirstNameUser = firstNameUser;
    }

    public String getLastNameUser() {
        return mLastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        mLastNameUser = lastNameUser;
    }

    public String getEmailUser() {
        return mEmailUser;
    }

    public void setEmailUser(String emailUser) {
        mEmailUser = emailUser;
    }

    public int getIdProfileUser() {
        return mIdProfileUser;
    }

    public void setIdProfileUser(int idProfileUser) {
        mIdProfileUser = idProfileUser;
    }

    public String getMidStatusUser() {
        return midStatusUser;
    }

    public void setMidStatusUser(String midStatusUser) {
        this.midStatusUser = midStatusUser;
    }

    public int getIdVeichleAsigned() {return mIdVeichleAsigned;}

    public void setIdVeichleAsigned(int idVeichleAsigned) {mIdVeichleAsigned = idVeichleAsigned;}
}