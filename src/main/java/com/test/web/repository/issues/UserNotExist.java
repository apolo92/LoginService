package com.test.web.repository.issues;

import sun.rmi.runtime.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by apolo on 03/10/2016.
 */
public class UserNotExist extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger( UserNotExist.class.getName() );

    public UserNotExist (String error){
       LOGGER.log(Level.SEVERE,error);
    }
}
