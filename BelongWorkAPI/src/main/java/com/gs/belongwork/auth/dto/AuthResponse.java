package com.gs.belongwork.auth.dto;

public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    public AuthResponse(){}
    public AuthResponse(String token, String username){this.token = token; this.username = username;}
    public String getToken(){return token;}
    public String getType(){return type;}
    public String getUsername(){return username;}
    public void setToken(String t){this.token = t;}
}
