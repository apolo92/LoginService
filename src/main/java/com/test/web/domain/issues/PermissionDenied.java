package com.test.web.domain.issues;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alejandro on 01/10/2016.
 */
public class PermissionDenied extends Exception {
    private static final Logger LOGGER = Logger.getLogger(PermissionDenied.class.getName());

    public  PermissionDenied(String error){
        LOGGER.log(Level.SEVERE,error);
    }

}
