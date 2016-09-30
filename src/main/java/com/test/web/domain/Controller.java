package com.test.web.domain;

import com.google.gson.Gson;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.net.httpserver.HttpExchange;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.test.web.domain.dto.UserDTO;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.utils.ReadResoures;
import com.test.web.repository.RepositoryUsers;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Controller {

    public static void loginPage(HttpExchange httpExchange) throws IOException {
        sendHttpResultOk(httpExchange, ReadResoures.readResource("login.html"));
    }

    public static void page(HttpExchange httpExchange) throws IOException {
        sendHttpResultOk(httpExchange, ReadResoures.readResource("page.html"));
    }

    public static void loginUser(HttpExchange httpExchange) throws IOException {
        UserDTO userDTO = (UserDTO) getBody(httpExchange.getRequestBody(),UserDTO.class);
        UserLogin userLogin = new UserLogin(userDTO);

        LoginService loginService = new LoginService();
        try {
            userLogin = loginService.loginUser(userLogin);
        } catch (LoginError loginError) {
            sendHttpErrorLogin(httpExchange);
        }

        userDTO.setLoadPage(userLogin.getRole().getUrl());
        sendHttpResultOk(httpExchange, getBytesDTOObject(userDTO));
    }

    private static void sendHttpErrorLogin(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(401, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }

    private static byte[] getBytesDTOObject(Object userLogin) {
        return new Gson().toJson(userLogin).getBytes();
    }

    private static void sendHttpResultOk(HttpExchange httpExchange, byte[] bytes) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static Object getBody(InputStream bodyStream, Class jsonClass) throws IOException {
        String json = IOUtils.toString(new InputStreamReader(bodyStream, "utf-8"));
        Object result = new Gson().fromJson(json, jsonClass);
        return result;
    }
}
