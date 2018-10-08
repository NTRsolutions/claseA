package com.apreciasoft.mobile.remisclasea.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/01/2017.
 */

public class TravelBodyEntity {


    /**
     * PARA ASIGNAR UN VIAJE
     */




    @Expose
    @SerializedName("idDriverKf")
    public int idDriverKf;

    @Expose
    @SerializedName("idTravel")
    public int idTravel;

    @Expose
    @SerializedName("isUserAsignedTravel")
    public int isUserAsignedTravel;


    @Expose
    @SerializedName("idUserCompanyKf")
    public int midUserCompanyKf;

    @Expose
    @SerializedName("idClientKf")
    public int mIdClientKf;

    @Expose
    @SerializedName("isTravelComany")
    public boolean mIsTravelComany;

    @Expose
    @SerializedName("idTypeVehicle")
    public int mIdTypeVehicle;


    @Expose
    @SerializedName("hoursAribo")
    public String mhoursAribo;


    @Expose
    @SerializedName("terminal")
    public String mterminal;

    @Expose
    @SerializedName("airlineCompany")
    public String mairlineCompany;

    @Expose
    @SerializedName("flyNumber")
    public String mflyNumber;


    @Expose
    @SerializedName("isFleetTravelAssistance")
    public int misFleetTravelAssistance;



    @Expose
    @SerializedName("origin")
    public OriginEntity mOrigin;

    @Expose
    @SerializedName("destination")
    public DestinationEntity mDestination;



    public OriginEntity getOrigin() {
        return mOrigin;
    }

    public void setOrigin(OriginEntity origin) {
        mOrigin = origin;
    }

    @Expose
    @SerializedName("dateTravel")
    public String mDateTravel;

    @Expose
    @SerializedName("isTravelSendMovil")
    public boolean misTravelSendMovil;

    @Expose
    @SerializedName("isFleetTravel")
    public boolean misFleetTravel;

    @Expose
    @SerializedName("distanceLabel")
    public double mdistanceLabel;


    @Expose
    @SerializedName("distance")
    public double mdistance;

    public double getMdistanceLabel() {
        return mdistanceLabel;
    }

    public void setMdistanceLabel(double mdistanceLabel) {
        this.mdistanceLabel = mdistanceLabel;
    }

    public double getMdistance() {
        return mdistance;
    }

    public void setMdistance(double mdistance) {
        this.mdistance = mdistance;
    }

    public int getMidUserCompanyKf() {
        return midUserCompanyKf;
    }

    public void setMidUserCompanyKf(int midUserCompanyKf) {
        this.midUserCompanyKf = midUserCompanyKf;
    }

    public boolean isMisTravelSendMovil() {
        return misTravelSendMovil;
    }

    public void setMisTravelSendMovil(boolean misTravelSendMovil) {
        this.misTravelSendMovil = misTravelSendMovil;
    }

    public String getDateTravel() {
        return mDateTravel;
    }

    public void setDateTravel(String dateTravel) {
        mDateTravel = dateTravel;
    }


    public boolean isTravelComany() {
        return mIsTravelComany;
    }


    public void setTravelComany(boolean travelComany) {
        mIsTravelComany = travelComany;
    }



    public int getIdClientKf() {

        return mIdClientKf;
    }

    public int getmIdTypeVehicle() {
        return mIdTypeVehicle;
    }

    public void setmIdTypeVehicle(int mIdTypeVehicle) {
        this.mIdTypeVehicle = mIdTypeVehicle;
    }

    public void setIdClientKf(int idClientKf) {
        mIdClientKf = idClientKf;
    }

    public TravelBodyEntity(int idClientKf, boolean isTravelComany, OriginEntity origin,
                            DestinationEntity dstination, String dateTravel,int idTypeVehicle, boolean isTravelSendMovil,
                            int idUserCompanyKf, String hoursAribo,
                                    String   terminal,
                                    String  airlineCompany,
                                    String flyNumber,
                            int isFleetTravelAssistance,boolean isFleetTravel,
                            double distanceLabel,
                            double distance) {
        mIdClientKf = idClientKf;
        mIsTravelComany = isTravelComany;
        mOrigin = origin;
        mDestination = dstination;
        mDateTravel = dateTravel;
        mIdTypeVehicle = idTypeVehicle;
        misTravelSendMovil = isTravelSendMovil;
        midUserCompanyKf = idUserCompanyKf;
        mhoursAribo = hoursAribo;
        mterminal = terminal;
        mairlineCompany = airlineCompany;
        mflyNumber = flyNumber;
        misFleetTravelAssistance = isFleetTravelAssistance;
        misFleetTravel = isFleetTravel;
        mdistanceLabel = distanceLabel;
        mdistance = distance;
    }

    public TravelBodyEntity(int idClientKf, boolean isTravelComany, OriginEntity origin,
                            DestinationEntity dstination, String dateTravel,int idTypeVehicle, boolean isTravelSendMovil,
                            int idUserCompanyKf, String hoursAribo,
                            String   terminal,
                            String  airlineCompany,
                            String flyNumber,
                            int isFleetTravelAssistance,boolean isFleetTravel,
                            double distanceLabel,
                            double distance,int idDriverKf) {
        mIdClientKf = idClientKf;
        mIsTravelComany = isTravelComany;
        mOrigin = origin;
        mDestination = dstination;
        mDateTravel = dateTravel;
        mIdTypeVehicle = idTypeVehicle;
        misTravelSendMovil = isTravelSendMovil;
        midUserCompanyKf = idUserCompanyKf;
        mhoursAribo = hoursAribo;
        mterminal = terminal;
        mairlineCompany = airlineCompany;
        mflyNumber = flyNumber;
        misFleetTravelAssistance = isFleetTravelAssistance;
        misFleetTravel = isFleetTravel;
        mdistanceLabel = distanceLabel;
        mdistance = distance;
        this.idDriverKf = idDriverKf;

    }

    public TravelBodyEntity(int idDriverKf, int idTravel, int isUserAsignedTravel) {
        this.idDriverKf = idDriverKf;
        this.idTravel = idTravel;
        this.isUserAsignedTravel = isUserAsignedTravel;
    }

    public int getmIdClientKf() {
        return mIdClientKf;
    }

    public void setmIdClientKf(int mIdClientKf) {
        this.mIdClientKf = mIdClientKf;
    }

    public boolean ismIsTravelComany() {
        return mIsTravelComany;
    }

    public void setmIsTravelComany(boolean mIsTravelComany) {
        this.mIsTravelComany = mIsTravelComany;
    }

    public OriginEntity getmOrigin() {
        return mOrigin;
    }

    public void setmOrigin(OriginEntity mOrigin) {
        this.mOrigin = mOrigin;
    }

    public DestinationEntity getmDestination() {
        return mDestination;
    }

    public void setmDestination(DestinationEntity mDestination) {
        this.mDestination = mDestination;
    }

    public String getmDateTravel() {
        return mDateTravel;
    }

    public void setmDateTravel(String mDateTravel) {
        this.mDateTravel = mDateTravel;
    }


    public String getMhoursAribo() {
        return mhoursAribo;
    }

    public void setMhoursAribo(String mhoursAribo) {
        this.mhoursAribo = mhoursAribo;
    }

    public String getMterminal() {
        return mterminal;
    }

    public void setMterminal(String mterminal) {
        this.mterminal = mterminal;
    }

    public String getMairlineCompany() {
        return mairlineCompany;
    }

    public void setMairlineCompany(String mairlineCompany) {
        this.mairlineCompany = mairlineCompany;
    }

    public String getMflyNumber() {
        return mflyNumber;
    }

    public void setMflyNumber(String mflyNumber) {
        this.mflyNumber = mflyNumber;
    }


    public int getMisFleetTravelAssistance() {
        return misFleetTravelAssistance;
}

    public void setMisFleetTravelAssistance(int misFleetTravelAssistance) {
        this.misFleetTravelAssistance = misFleetTravelAssistance;
    }

    public boolean isMisFleetTravel() {
        return misFleetTravel;
    }

    public void setMisFleetTravel(boolean misFleetTravel) {
        this.misFleetTravel = misFleetTravel;
    }

    public int getIdDriverKf() {
        return idDriverKf;
    }

    public void setIdDriverKf(int idDriverKf) {
        this.idDriverKf = idDriverKf;
    }

    public int getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(int idTravel) {
        this.idTravel = idTravel;
    }

    public int getIsUserAsignedTravel() {
        return isUserAsignedTravel;
    }

    public void setIsUserAsignedTravel(int isUserAsignedTravel) {
        this.isUserAsignedTravel = isUserAsignedTravel;
    }
}


