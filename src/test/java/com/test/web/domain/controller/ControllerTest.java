package com.test.web.domain.controller;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;
import org.junit.Test;
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
public class ControllerTest {

    private HttpExchange http = Mockito.mock(HttpExchange.class);
    private OutputStream output = Mockito.mock(OutputStream.class);

    @Test
    public void loginPageReturnedLoginHTML() throws IOException {
        Mockito.when(http.getResponseBody()).thenReturn(output);

        Controller.loginPage(http);

        Mockito.verify(output).close();
    }

    @Test
    public void pageReturnedPageHTML() throws IOException, URISyntaxException {

        URI uri = new URI("http://localhost:8000/page1?"+getValidJWT());
        Mockito.when(http.getResponseBody()).thenReturn(output);
        Mockito.when(http.getRequestURI()).thenReturn(uri);

        Controller.page(http);

        Mockito.verify(output).close();
    }

    @Test
    public void pageReturnedErrorLogin() throws IOException, URISyntaxException {
        URI uri = new URI("http://localhost:8000/page1?"+getValidJWT()+"error");
        Mockito.when(http.getResponseBody()).thenReturn(output);
        Mockito.when(http.getRequestURI()).thenReturn(uri);

        Controller.page(http);

        Mockito.verify(http).sendResponseHeaders(401,0);
    }

    @Test
    public void loginUserIsCorrect() throws IOException {
        JsonObject stub = new JsonObject();
        stub.addProperty("username","test");
        stub.addProperty("password","test");
        InputStream input = new ByteArrayInputStream(stub.toString().getBytes());
        Mockito.when(http.getRequestBody()).thenReturn(input);
        Mockito.when(http.getResponseBody()).thenReturn(output);
        loadUserLogin();

        Controller.loginUser(http);

        Mockito.verify(http).sendResponseHeaders(200,0);
    }

    private static String getValidJWT() {
        UserLogin user = loadUserLogin();
        String jwt = Factory.getLoginServiceInstance().generateJWT(user);
        return jwt;
    }

    private static UserLogin loadUserLogin() {
        List<Roles> role = new ArrayList<>();
        role.add(Roles.ROLE1);
        UserLogin user = new UserLogin("test","test",role);
        Factory.getRepositoryInstance().loadUser(user);
        return user;
    }
}
