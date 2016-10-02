package com.test.web.dto;

import com.test.web.domain.model.constants.Roles;

import java.util.List;

/**
 * Created by alejandro on 30/09/2016.
 */
public class UserPageDTO extends UserDTO {
    private String loadPage;
    private String jwt;

    public UserPageDTO(String username, List<Roles> role){
        super(username, role);
    }

    public void setLoadPage(String loadPage) {
        this.loadPage = loadPage;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
