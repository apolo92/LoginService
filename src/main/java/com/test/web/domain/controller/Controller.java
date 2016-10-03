package com.test.web.domain.controller;

import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.controller.utils.ControllerUtils;
import com.test.web.domain.controller.utils.ReadResoures;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.services.LoginService;
import com.test.web.dto.UserPageDTO;

import java.io.IOException;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Controller {

    public static void loginPage(HttpExchange httpExchange) throws IOException {
        ControllerUtils.sendHttpResult(httpExchange, ReadResoures.readResource("login.html"),200);
    }

    public static void page(HttpExchange httpExchange) throws IOException {
        String username = ControllerUtils.autenticate(httpExchange);
        if (username !=null) {
            ControllerUtils.sendHttpResult(httpExchange, ReadResoures.readResourceAndInsertName("page.html", username), 200);
        }
    }

    public static void loginUser(HttpExchange httpExchange) throws IOException {
        UserPageDTO userDTO = (UserPageDTO) ControllerUtils.getBody(httpExchange.getRequestBody(),UserPageDTO.class);
        UserLogin userLogin = new UserLogin(userDTO);
        LoginService loginService = Factory.getLoginServiceInstance();
        try {
            userLogin = loginService.loginUser(userLogin);
            userDTO.setJwt(loginService.generateJWT(userLogin));
        } catch (LoginError loginError) {
            ControllerUtils.sendHttpResult(httpExchange,401);
        }

        userDTO.setLoadPage(userLogin.getRole().get(0).getUrl()[0]);
        ControllerUtils.sendHttpResult(httpExchange, ControllerUtils.getBytesDTOObject(userDTO),200);
    }
}
