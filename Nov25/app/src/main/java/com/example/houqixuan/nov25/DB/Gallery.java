package com.example.houqixuan.nov25.DB;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by houqixuan on 11/25/16.
 */

public class Gallery implements Parcelable {

    private String path;
    private String name;
    private Date date;

    public Gallery() {
        super();
    }

    private Gallery(Parcel in) {
        super();
        this.path = in.readString();
        this.name = in.readString();
        this.date = new Date(in.readLong());
    }


    public String getPath() {
        return path;
    }

    public void setPath(String string) {
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Gallery [path=" + path + ", name=" + name + ", date="
                + date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + path.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gallery other = (Gallery) obj;
        if (path != other.path)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getPath());
        parcel.writeString(getName());
        parcel.writeLong(getDate().getTime());

    }

    public static final Parcelable.Creator<Gallery> CREATOR = new Parcelable.Creator<Gallery>() {
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };
}
