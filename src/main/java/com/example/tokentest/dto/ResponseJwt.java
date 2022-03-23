package com.example.tokentest.dto;

public class ResponseJwt {
    private String token;
    private String refToken;
    private String type = "Bearer";

    public ResponseJwt(String accessToken, String refreshToken) {
        this.token = accessToken;
        this.refToken = refreshToken;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getRefreshToken() {
        return refToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refToken = refreshToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
