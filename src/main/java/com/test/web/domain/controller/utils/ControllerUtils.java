package com.test.web.domain.controller.utils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.issues.InvalidUsser;
import com.test.web.domain.issues.PermissionDenied;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by alejandro on 02/10/2016.
 */
public class ControllerUtils {

    public static byte[] getBytesDTOObject(Object userLogin) {
        return new Gson().toJson(userLogin).getBytes();
    }

    public static void sendHttpResult(HttpExchange httpExchange, byte[] bytes ,int header) throws IOException {
        httpExchange.sendResponseHeaders(header, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    public static Object getBody(InputStream bodyStream, Class jsonClass) throws IOException {
        String json = IOUtils.toString(new InputStreamReader(bodyStream, "utf-8"));
        Object result = new Gson().fromJson(json, jsonClass);
        return result;
    }

    public static void sendHttpResult(HttpExchange httpExchange, int header) throws IOException {
        httpExchange.sendResponseHeaders(header, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }

    public static String autenticate(HttpExchange httpExchange) throws IOException {
        String jwt = httpExchange.getRequestURI().getQuery();
        String urlAccess ="http://localhost:8000";
        urlAccess += httpExchange.getRequestURI().getPath();
        String username = null;

        try {
            username = Factory.getLoginServiceInstance().validateJWTAmdPermissions(jwt, urlAccess);
        } catch (InvalidUsser invalidUsser) {
            ControllerUtils.sendHttpResult(httpExchange, ReadResoures.readResource("login.html"),401);
        } catch (PermissionDenied permissionDenied){
            ControllerUtils.sendHttpResult(httpExchange,ReadResoures.readResource("accesDenied.html"),401);
        }
        return username;
    }
}
