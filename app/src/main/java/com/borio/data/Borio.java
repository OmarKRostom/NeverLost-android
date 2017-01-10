package com.borio.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Borio {

    @JsonField(name = "data")
    private String encryptedData;
    @JsonField
    private String iv;
    @JsonField
    private String salt;

    public Borio() {
    }

    public Borio(String encryptedData, String iv, String salt) {
        this.encryptedData = encryptedData;
        this.iv = iv;
        this.salt = salt;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
