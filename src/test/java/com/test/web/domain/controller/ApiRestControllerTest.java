package com.test.web.domain.controller;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.model.UserLogin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by apolo on 03/10/2016.
 */
public class ApiRestControllerTest {
    private HttpExchange http = Mockito.mock(HttpExchange.class);
    private OutputStream output = Mockito.mock(OutputStream.class);

    @Before
    public void setUp(){
        InputStream input = getInputStreamWithJsonDTO();
        Mockito.when(http.getRequestBody()).thenReturn(input);
        Mockito.when(http.getResponseBody()).thenReturn(output);
    }

    @Test
    public void getAllUsersInRestPetition() throws IOException {
        Mockito.when(http.getRequestMethod()).thenReturn("GET");

        ApiRestController.users(http);

        Mockito.verify(http).sendResponseHeaders(200, 0);
    }

    @Test
    public void insertNewUserByApiRest() throws IOException {
        Mockito.when(http.getRequestMethod()).thenReturn("POST");

        ApiRestController.users(http);

        Mockito.verify(http).sendResponseHeaders(201, 0);
    }

    @Test
    public void updateUserByApiRest() throws IOException {
        Mockito.when(http.getRequestMethod()).thenReturn("PUT");
        Factory.getRepositoryInstance().loadUser(new UserLogin("test","test"));

        ApiRestController.users(http);

        Mockito.verify(http).sendResponseHeaders(200, 0);
    }

    private InputStream getInputStreamWithJsonDTO() {
        JsonObject stub = new JsonObject();
        stub.addProperty("username","test");
        stub.addProperty("password","test");
        stub.addProperty("replaceUserName","test2");
        return new ByteArrayInputStream(stub.toString().getBytes());
    }
}
