package com.test.web.domain.model;

import com.test.web.dto.UserDTO;
import com.test.web.domain.model.constants.Roles;

/**
 * Created by alejandro on 29/09/2016.
 */
public class UserLogin {

    private String userName;
    private String password;
    private Roles role;

    public UserLogin(String userName, String password, Roles role){
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
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }
}
