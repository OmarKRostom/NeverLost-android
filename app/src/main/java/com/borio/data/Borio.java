package com.borio.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject
public class Borio {

    @JsonField
    private String username;

    @JsonField(name = "data")
    private List<ProviderPassword> passwords;

    public Borio() {
    }

    public Borio(String username, List<ProviderPassword> passwords) {
        this.username = username;
        this.passwords = passwords;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProviderPassword> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<ProviderPassword> passwords) {
        this.passwords = passwords;
    }

}
