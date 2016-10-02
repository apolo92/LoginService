package com.test.web.domain.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.factory.FactoryRepository;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.utils.ControllerUtils;
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
                ControllerUtils.sendHttpResultOk(httpExchange,new Gson().toJson(dtoUsers).getBytes());
                break;
            case "POST":
                UserLogin userLogin = new UserLogin(dtoUser);
                FactoryRepository.getRepositoryInstance().loadUser(userLogin);
                ControllerUtils.sendHttpCreatedOk(httpExchange);
                break;
            case "PUT":
                FactoryRepository.getRepositoryInstance().modifyUser(dtoUser);
                ControllerUtils.sendHttpResultOk(httpExchange);
                break;
            default:
                ControllerUtils.sendHttpErrorMethod(httpExchange);
        }
    }

    private static List<UserPageDTO> getAllUsers() {
        List<UserPageDTO> result = new ArrayList<>();
        List<UserLogin> users = FactoryRepository.getRepositoryInstance().searchAllUsersStored();
        users.stream().forEach(userLogin ->{
            UserPageDTO dto = new UserPageDTO(userLogin.getUserName(),userLogin.getRole());
            result.add(dto);
        });
        return result;
    }
}
