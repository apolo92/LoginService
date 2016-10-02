package com.test.web.domain.utils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by alejandro on 02/10/2016.
 */
public class ControllerUtils {

    public static void sendHttpErrorLogin(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(401, 0);
        sendHttpEmpty(httpExchange);
    }

    public static void sendHttpErrorPermissionDenied(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(401, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(ReadResoures.readResource("accesDenied.html"));
        os.close();
    }

    public static byte[] getBytesDTOObject(Object userLogin) {
        return new Gson().toJson(userLogin).getBytes();
    }

    public static void sendHttpResultOk(HttpExchange httpExchange, byte[] bytes) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    public static void sendHttpResultOk(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }

    public static Object getBody(InputStream bodyStream, Class jsonClass) throws IOException {
        String json = IOUtils.toString(new InputStreamReader(bodyStream, "utf-8"));
        Object result = new Gson().fromJson(json, jsonClass);
        return result;
    }

    public static void sendHttpErrorMethod(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(400, 0);
        sendHttpEmpty(httpExchange);
    }

    public static void sendHttpCreatedOk(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(201, 0);
        sendHttpEmpty(httpExchange);
    }

    private static void sendHttpEmpty(HttpExchange httpExchange) throws IOException {
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }
}
