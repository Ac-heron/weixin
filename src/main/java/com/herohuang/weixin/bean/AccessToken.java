package com.herohuang.weixin.bean;

/**
 * Created by Acheron on 2015/12/8.
 */
public class AccessToken {
    
    private  String token;
    private Integer expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
