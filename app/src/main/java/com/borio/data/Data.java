package com.borio.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject
public class Data {

    @JsonField
    private String username;

    @JsonField(name = "providers_infos")
    private List<ProviderInfo> providerInfos;

    public Data() {
    }

    public Data(String username, List<ProviderInfo> providerInfos) {
        this.username = username;
        this.providerInfos = providerInfos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProviderInfo> getProviderInfos() {
        return providerInfos;
    }

    public void setProviderInfos(List<ProviderInfo> providerInfos) {
        this.providerInfos = providerInfos;
    }

}
