package com.borio.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ProviderInfo implements Parcelable {

    public static final Creator<ProviderInfo> CREATOR = new Creator<ProviderInfo>() {
        @Override
        public ProviderInfo createFromParcel(Parcel in) {
            return new ProviderInfo(in);
        }

        @Override
        public ProviderInfo[] newArray(int size) {
            return new ProviderInfo[size];
        }
    };

    @JsonField
    private String provider;
    @JsonField
    private String username;
    @JsonField
    private String password;

    public ProviderInfo() {
    }

    public ProviderInfo(String provider, String password) {
        this.provider = provider;
        this.password = password;
    }

    public ProviderInfo(Parcel in) {
        this.provider = in.readString();
        this.username = in.readString();
        this.password = in.readString();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(provider);
        dest.writeString(username);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
