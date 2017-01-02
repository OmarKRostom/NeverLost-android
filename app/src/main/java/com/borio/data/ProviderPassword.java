package com.borio.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ProviderPassword {

    @JsonField
    private String provider;
    @JsonField
    private String password;

    public ProviderPassword() {
    }

    public ProviderPassword(String provider, String password) {
        this.provider = provider;
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
