package com.practice.bantaicovid_19.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

public class CallCenter implements Parcelable {

    private String provinsi, callCenter;

    public CallCenter (String provinsi, String callCenter){
        this.provinsi = provinsi;
        this.callCenter = callCenter;
    }

    public CallCenter(){

    }

    protected CallCenter(Parcel in){
        provinsi = in.readString();
        callCenter = in.readString();
    }

    public static final Creator<CallCenter> CREATOR = new Creator<CallCenter>() {
        @Override
        public CallCenter createFromParcel(Parcel in) { return new CallCenter(in); }

        @Override
        public CallCenter[] newArray(int size) { return new CallCenter[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(provinsi);
        dest.writeString(callCenter);
    }
    public String getProvinsi() { return provinsi; }

    public void setProvinsi(String provinsi) { this.provinsi = provinsi; }

    public String getCallCenter() { return callCenter; }

    public void setCallCenter(String callCenter) { this.callCenter = callCenter; }

}
