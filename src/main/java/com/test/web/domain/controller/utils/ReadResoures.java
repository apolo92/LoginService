package com.test.web.domain.controller.utils;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.test.web.domain.issues.ResourceException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by alejandro on 28/09/2016.
 */
public class ReadResoures {

    public static byte[] readResource(String resouceName) {
        try {
            return Files.readAllBytes(getPath(resouceName));
        } catch (IOException e) {
            throw new ResourceException(e);
        } catch (URISyntaxException e) {
            throw new ResourceException(e);
        }
    }

    public static byte[] readResourceAndInsertName(String resouceName, String userName) {
        try {
            List<String> htmlString = Files.readAllLines(getPath(resouceName));
            return htmlString.stream().map(e -> e.toString()).reduce("", String::concat).replace("{USER_NAME}",userName).getBytes();
        } catch (IOException e) {
            throw new ResourceException(e);
        } catch (URISyntaxException e) {
            throw new ResourceException(e);
        }
    }

    private static Path getPath(String resouceName) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(resouceName).toURI());
    }
}
