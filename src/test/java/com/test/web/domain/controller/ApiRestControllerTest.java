package com.test.web.domain.controller;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    public void insertNewUserByApiRest() throws IOException, URISyntaxException {
        Mockito.when(http.getRequestMethod()).thenReturn("POST");

        URI uri = new URI("http://localhost:8000/users?"+getValidJWT());
        Mockito.when(http.getRequestURI()).thenReturn(uri);

        ApiRestController.users(http);

        Mockito.verify(http).sendResponseHeaders(201, 0);
    }

    @Test
    public void updateUserByApiRest() throws IOException, URISyntaxException {
        Mockito.when(http.getRequestMethod()).thenReturn("PUT");
        Factory.getRepositoryInstance().loadUser(new UserLogin("test","test"));

        URI uri = new URI("http://localhost:8000/users?"+getValidJWT());
        Mockito.when(http.getRequestURI()).thenReturn(uri);


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

    private static String getValidJWT() {
        UserLogin user = loadUserLogin();
        String jwt = Factory.getLoginServiceInstance().generateJWT(user);
        return jwt;
    }

    private static UserLogin loadUserLogin() {
        List<Roles> role = new ArrayList<>();
        role.add(Roles.ADMIN);
        UserLogin user = new UserLogin("test3","test3",role);
        Factory.getRepositoryInstance().loadUser(user);
        return user;
    }
}
