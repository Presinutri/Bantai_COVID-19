package com.practice.bantaicovid_19.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

public class Tips implements Parcelable {

    private String title;
    private String link;
    private String content;
    private int photo;

    public Tips(String title, String link, String content, int photo) {
        this.title = title;
        this.link = link;
        this.content = content;
        this.photo = photo;
    }

    protected Tips(Parcel in) {
        title = in.readString();
        link = in.readString();
        content = in.readString();
        photo = in.readInt();
    }

    public static final Creator<Tips> CREATOR = new Creator<Tips>() {
        @Override
        public Tips createFromParcel(Parcel in) {
            return new Tips(in);
        }

        @Override
        public Tips[] newArray(int size) {
            return new Tips[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeString(content);
        parcel.writeInt(photo);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
