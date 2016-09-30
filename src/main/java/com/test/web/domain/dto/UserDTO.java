package com.test.web.domain.dto;

/**
 * Created by alejandro on 30/09/2016.
 */
public class UserDTO {
    private String username;
    private String password;
    private String loadPage;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setLoadPage(String loadPage) {
        this.loadPage = loadPage;
    }
}
