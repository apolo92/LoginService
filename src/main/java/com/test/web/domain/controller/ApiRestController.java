package com.test.web.domain.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.controller.utils.ControllerUtils;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.model.UserLogin;
import com.test.web.dto.UserAPIDTO;
import com.test.web.dto.UserPageDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 02/10/2016.
 */
public class ApiRestController {
    public static void users(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        UserAPIDTO dtoUser = (UserAPIDTO) ControllerUtils.getBody(httpExchange.getRequestBody(),UserAPIDTO.class);
        switch (method) {
            case "GET":
                List<UserPageDTO> dtoUsers = getAllUsers();
                ControllerUtils.sendHttpResult(httpExchange,new Gson().toJson(dtoUsers).getBytes(),200);
                break;
            case "POST":
                ControllerUtils.autenticate(httpExchange);
                UserLogin userLogin = new UserLogin(dtoUser);
                Factory.getRepositoryInstance().loadUser(userLogin);
                ControllerUtils.sendHttpResult(httpExchange,201);
                break;
            case "PUT":
                ControllerUtils.autenticate(httpExchange);
                Factory.getRepositoryInstance().modifyUser(dtoUser);
                ControllerUtils.sendHttpResult(httpExchange,200);
                break;
            default:
                ControllerUtils.sendHttpResult(httpExchange,400);
                break;
        }
    }

    private static List<UserPageDTO> getAllUsers() {
        List<UserPageDTO> result = new ArrayList<>();
        List<UserLogin> users = Factory.getRepositoryInstance().searchAllUsersStored();
        users.stream().forEach(userLogin ->{
            UserPageDTO dto = new UserPageDTO(userLogin.getUserName(),userLogin.getRole());
            result.add(dto);
        });
        return result;
    }
}
