package com.test.web.dto;

/**
 * Created by alejandro on 30/09/2016.
 */
public class UserDTO {
    private String username;
    private String password;
    private String loadPage;
    private String jwt;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setLoadPage(String loadPage) {
        this.loadPage = loadPage;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
