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
            throw new ResourceException("Error in load resource:"+resouceName);
        } catch (URISyntaxException e) {
            throw new ResourceException("Error in load resource:"+resouceName);
        }
    }

    public static byte[] readResourceAndInsertName(String resouceName, String userName) {
        try {
            List<String> htmlString = Files.readAllLines(getPath(resouceName));
            return htmlString.stream().map(e -> e.toString()).reduce("", String::concat).replace("{USER_NAME}",userName).getBytes();
        } catch (IOException e) {
            throw new ResourceException("Error in load resource:"+resouceName);
        } catch (URISyntaxException e) {
            throw new ResourceException("Error in load resource:"+resouceName);
        }
    }

    private static Path getPath(String resouceName) throws URISyntaxException {
        String path = ClassLoader.getSystemResource(resouceName).toURI().toString();
        // differentiate is compile jar or IDE run the path is different
        if (path.startsWith("jar")){
            return Paths.get(resouceName);
        }
        return Paths.get(ClassLoader.getSystemResource(resouceName).toURI());
    }
}
