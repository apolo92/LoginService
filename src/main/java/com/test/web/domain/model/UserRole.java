package com.test.web.domain.model;

/**
 * Created by alejandro on 30/09/2016.
 */
public class UserRole {

    private String url;
    private String role;

    public UserRole(String url,String role){
        this.url = url;
        this.role = role;
    }

    public String getUrl() {
        return url;
    }
}
