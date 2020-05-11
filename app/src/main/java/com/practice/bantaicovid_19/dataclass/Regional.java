package com.practice.bantaicovid_19.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

public class Regional implements Parcelable {
    private String province;
    private int regionalPositive;
    private int regionalCured;
    private int regionalDeath;

    public Regional(String province, int regionalPositive, int regionalCured, int regionalDeath) {
        this.province = province;
        this.regionalPositive = regionalPositive;
        this.regionalCured = regionalCured;
        this.regionalDeath = regionalDeath;
    }

    public Regional() {

    }

    protected Regional(Parcel in) {
        province = in.readString();
        regionalPositive = in.readInt();
        regionalCured = in.readInt();
        regionalDeath = in.readInt();
    }

    public static final Creator<Regional> CREATOR = new Creator<Regional>() {
        @Override
        public Regional createFromParcel(Parcel in) {
            return new Regional(in);
        }

        @Override
        public Regional[] newArray(int size) {
            return new Regional[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(province);
        parcel.writeInt(regionalPositive);
        parcel.writeInt(regionalCured);
        parcel.writeInt(regionalDeath);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getRegionalPositive() {
        return regionalPositive;
    }

    public void setRegionalPositive(int regionalPositive) {
        this.regionalPositive = regionalPositive;
    }

    public int getRegionalCured() {
        return regionalCured;
    }

    public void setRegionalCured(int regionalCured) {
        this.regionalCured = regionalCured;
    }

    public int getRegionalDeath() {
        return regionalDeath;
    }

    public void setRegionalDeath(int regionalDeath) {
        this.regionalDeath = regionalDeath;
    }

    public static Creator<Regional> getCREATOR() {
        return CREATOR;
    }
}
