package com.apreciasoft.mobile.remisclasea.Util;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.apreciasoft.mobile.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.mobile.remisclasea.Entity.InfoTravelEntityLite;
import com.apreciasoft.mobile.remisclasea.Entity.TravelLocationEntity;
import com.apreciasoft.mobile.remisclasea.Entity.VehicleType;
import com.apreciasoft.mobile.remisclasea.Entity.client;
import com.apreciasoft.mobile.remisclasea.Entity.notification;
import com.apreciasoft.mobile.remisclasea.Entity.paramEntity;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;

import java.util.List;

/**
 * Created by Admin on 02-01-2017.
 */

 public class GlovalVar extends Application {

    private String gv_base_intance = HttpConexion.instance;
    private String gv_user_name;
    private int gv_user_id;
    private String gv_user_mail;
    private  int gv_id_profile;
    private int gv_id_cliet;
    private  int gv_id_driver;
    private  int gv_id_vehichle;
    private  String gv_uri;
    private InfoTravelEntity gv_travel_current;
    private InfoTravelEntityLite gv_travel_current_lite;

    private List<paramEntity> gv_param;
    private Boolean gv_logeed;
    private int gv_srviceActive;
    private TravelLocationEntity locationDriverFromClient;
    private List<notification> gv_listNotifications;
    private List<InfoTravelEntity> gv_lisReservations;
    //private driver gv_driverinfo;
    private client gv_clientinfo;
    private List<VehicleType> gv_listvehicleType;
    private String gv_nr_driver = "";

    private int gv_hour_init_travel;



    private  String gv_idResourceSocket;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }



    public GlovalVar() {
        this.gv_user_name = "";
        this.gv_user_id = 0;
        this.gv_user_mail = "";
        this.gv_id_profile = 0;
        this.gv_id_cliet = 0;
        this.gv_id_driver = 0;
        this.gv_id_vehichle = 0;
        this.gv_uri = "";
        this.gv_travel_current = null;
        this.gv_param = null;
        this.gv_logeed = false;
        this.gv_listvehicleType = null;
        this.gv_nr_driver = "";
    }

    public String getGv_idResourceSocket() {
        return gv_idResourceSocket;
    }

    public void setGv_idResourceSocket(String gv_idResourceSocket) {
        this.gv_idResourceSocket = gv_idResourceSocket;
    }

    public List<InfoTravelEntity> getGv_lisReservations() {
        return gv_lisReservations;
    }

    public void setGv_lisReservations(List<InfoTravelEntity> gv_lisReservations) {
        this.gv_lisReservations = gv_lisReservations;
    }

    public List<VehicleType> getGv_listvehicleType() {
        return gv_listvehicleType;
    }

    public void setGv_listvehicleType(List<VehicleType> gv_listvehicleType) {
        this.gv_listvehicleType = gv_listvehicleType;
    }

    public String getGv_base_intance() {
        return gv_base_intance;
    }

    public void setGv_base_intance(String gv_base_intance) {
        this.gv_base_intance = gv_base_intance;
    }

   /* public driver getGv_driverinfo() {
        return gv_driverinfo;
    }

    public void setGv_driverinfo(driver gv_driverinfo) {
        this.gv_driverinfo = gv_driverinfo;
    }*/

    public TravelLocationEntity getLocationDriverFromClient() {
        return locationDriverFromClient;
    }

    public void setLocationDriverFromClient(TravelLocationEntity locationDriverFromClient) {
        this.locationDriverFromClient = locationDriverFromClient;
    }

    public Boolean getGv_logeed() {
        return gv_logeed;
    }

    public void setGv_logeed(Boolean gv_logeed) {
        this.gv_logeed = gv_logeed;
    }

    public List<paramEntity> getGv_param() {
        return gv_param;
    }

    public void setGv_param(List<paramEntity> gv_param) {
        this.gv_param = gv_param;
    }

    public InfoTravelEntity getGv_travel_current() {
        return gv_travel_current;
    }

    public void setGv_travel_current(InfoTravelEntity gv_travel_current) {
        this.gv_travel_current = gv_travel_current;
    }

    public String getGv_uri() {
        return gv_uri;
    }

    public void setGv_uri(String gv_uri) {
        this.gv_uri = gv_uri;
    }

    public int getGv_id_vehichle() {
        return gv_id_vehichle;
    }

    public void setGv_id_vehichle(int gv_id_vehichle) {
        this.gv_id_vehichle = gv_id_vehichle;
    }

    public int getGv_id_profile() {
        return gv_id_profile;
    }

    public void setGv_id_profile(int gv_id_profile) {
        this.gv_id_profile = gv_id_profile;
    }

    public int getGv_id_cliet() {
        return gv_id_cliet;
    }

    public void setGv_id_cliet(int gv_id_cliet) {
        this.gv_id_cliet = gv_id_cliet;
    }

    public int getGv_id_driver() {
        return gv_id_driver;
    }

    public void setGv_id_driver(int gv_id_driver) {
        this.gv_id_driver = gv_id_driver;
    }

    public String getGv_user_name() {
        return gv_user_name;
    }

    public void setGv_user_name(String gv_user_name) {
        this.gv_user_name = gv_user_name;
    }

    public int getGv_user_id() {
        return gv_user_id;
    }

    public void setGv_user_id(int gv_user_id) {
        this.gv_user_id = gv_user_id;
    }

    public String getGv_user_mail() {
        return gv_user_mail;
    }

    public void setGv_user_mail(String gv_user_mail) {
        this.gv_user_mail = gv_user_mail;
    }


    public List<notification> getGv_listNotifications() {
        return gv_listNotifications;
    }

    public void setGv_listNotifications(List<notification> gv_listNotifications) {
        this.gv_listNotifications = gv_listNotifications;
    }

    public client getGv_clientinfo() {
        return gv_clientinfo;
    }

    public void setGv_clientinfo(client gv_clientinfo) {
        this.gv_clientinfo = gv_clientinfo;
    }


    public int getGv_srviceActive() {
        return gv_srviceActive;
    }

    public void setGv_srviceActive(int gv_srviceActive) {
        this.gv_srviceActive = gv_srviceActive;
    }

    public String getGv_nr_driver() {
        return gv_nr_driver;
    }

    public void setGv_nr_driver(String gv_nr_driver) {
        this.gv_nr_driver = gv_nr_driver;
    }

    public int getGv_hour_init_travel() {
        return gv_hour_init_travel;
    }

    public void setGv_hour_init_travel(int gv_hour_init_travel) {
        this.gv_hour_init_travel = gv_hour_init_travel;
    }

    public InfoTravelEntityLite getGv_travel_current_lite() {
        return gv_travel_current_lite;
    }

    public void setGv_travel_current_lite(InfoTravelEntityLite gv_travel_current_lite) {
        this.gv_travel_current_lite = gv_travel_current_lite;
    }
}
