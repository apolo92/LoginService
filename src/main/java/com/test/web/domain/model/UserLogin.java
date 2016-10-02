package com.test.web.domain.model;

import com.test.web.domain.model.constants.Roles;
import com.test.web.dto.UserDTO;

import java.util.List;

/**
 * Created by alejandro on 29/09/2016.
 */
public class UserLogin {

    private String userName;
    private String password;
    private List<Roles> role;
    private boolean logout;

    public UserLogin(String userName, String password, List<Roles> role){
        this.userName=userName;
        this.password=password;
        this.role=role;
    }

    public UserLogin(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    public UserLogin(UserDTO userDTO) {
        this.userName=userDTO.getUsername();
        this.password=userDTO.getPassword();
        this.role=userDTO.getRole();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Roles> getRole() {
        return role;
    }

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }
}
