package com.test.web.dto;

import com.test.web.domain.model.constants.Roles;

import java.util.List;

/**
 * Created by alejandro on 02/10/2016.
 */
public class UserAPIDTO extends UserDTO {

    private String replaceUserName;

    public UserAPIDTO(String username, List<Roles> role) {
        super(username, role);
    }

    public UserAPIDTO(String username, List<Roles> role,String replaceUserName) {
        super(username, role);
        this.replaceUserName=replaceUserName;
    }

    public String getReplaceUserName() {
        return replaceUserName;
    }
}
