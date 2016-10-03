package com.test.web.domain.issues;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alejandro on 28/09/2016.
 */
public class ResourceException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(ResourceException.class.getName());

    public ResourceException(String error){
        LOGGER.log(Level.SEVERE,error);
    }
}
