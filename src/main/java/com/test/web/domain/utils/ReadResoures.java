package com.test.web.domain.utils;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.test.web.domain.issues.ResourceException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by alejandro on 28/09/2016.
 */
public class ReadResoures {

    public static byte[] readResource(String resouceName) {
        try {
            return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resouceName).toURI()));
        } catch (IOException e) {
            throw new ResourceException(e);
        } catch (URISyntaxException e) {
            throw new ResourceException(e);
        }
    }


}
