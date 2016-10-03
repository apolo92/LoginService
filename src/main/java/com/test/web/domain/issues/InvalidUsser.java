package com.test.web.domain.issues;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alejandro on 01/10/2016.
 */
public class InvalidUsser extends Exception {
    private static final Logger LOGGER = Logger.getLogger(InvalidUsser.class.getName());

    public InvalidUsser(String error){
        LOGGER.log(Level.SEVERE,error);
    }
}
