package com.test.web.domain.controller;

import com.sun.net.httpserver.HttpExchange;
import com.test.web.domain.services.LoginService;
import com.test.web.domain.issues.InvalidUsser;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.issues.PermissionDenied;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.utils.ControllerUtils;
import com.test.web.domain.utils.ReadResoures;
import com.test.web.dto.UserPageDTO;

import java.io.IOException;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Controller {

    private static LoginService loginService = new LoginService();

    public static void loginPage(HttpExchange httpExchange) throws IOException {
        ControllerUtils.sendHttpResultOk(httpExchange, ReadResoures.readResource("login.html"));
    }

    public static void page(HttpExchange httpExchange) throws IOException {
        String jwt = httpExchange.getRequestURI().getQuery();
        String urlAccess ="http://localhost:8000";
        urlAccess += httpExchange.getRequestURI().getPath();
        String username = null;

        try {
            username = loginService.validateJWTAmdPermissions(jwt, urlAccess);
        } catch (InvalidUsser invalidUsser) {
            ControllerUtils.sendHttpResultOk(httpExchange, ReadResoures.readResource("login.html"));
        } catch (PermissionDenied permissionDenied){
            ControllerUtils.sendHttpErrorPermissionDenied(httpExchange);
        }

        ControllerUtils.sendHttpResultOk(httpExchange, ReadResoures.readResourceAndInsertName("page.html",username));
    }

    public static void loginUser(HttpExchange httpExchange) throws IOException {
        UserPageDTO userDTO = (UserPageDTO) ControllerUtils.getBody(httpExchange.getRequestBody(),UserPageDTO.class);
        UserLogin userLogin = new UserLogin(userDTO);

        try {
            userLogin = loginService.loginUser(userLogin);
            userDTO.setJwt(loginService.generateJWT(userLogin));
        } catch (LoginError loginError) {
            ControllerUtils.sendHttpErrorLogin(httpExchange);
        }

        userDTO.setLoadPage(userLogin.getRole().get(0).getUrl()[0]);
        ControllerUtils.sendHttpResultOk(httpExchange, ControllerUtils.getBytesDTOObject(userDTO));
    }

}
