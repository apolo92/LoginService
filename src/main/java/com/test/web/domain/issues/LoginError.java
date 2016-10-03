package com.test.web.domain.issues;

import com.test.web.repository.issues.UserNotExist;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alejandro on 30/09/2016.
 */
public class LoginError extends Exception {
    private static final Logger LOGGER = Logger.getLogger(LoginError.class.getName());

    public LoginError(String error){
        LOGGER.log(Level.SEVERE,error);
    }
}
