package com.test.web.dto;

import com.test.web.domain.model.constants.Roles;

import java.util.List;

/**
 * Created by alejandro on 02/10/2016.
 */
public class UserDTO {
    protected String username;
    protected List<Roles> role;
    private String password;

    public UserDTO(String username, List<Roles> role) {
        this.username=username;
        this.role=role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Roles> getRole() {
        return role;
    }
}
