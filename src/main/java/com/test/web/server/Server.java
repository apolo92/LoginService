package com.test.web.server;

import com.sun.net.httpserver.HttpServer;
import com.test.web.domain.controller.ApiRestController;
import com.test.web.domain.controller.Controller;
import com.test.web.domain.factory.Factory;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        loadDefaultUsers();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);

        server.createContext("/login", Controller::loginPage);
        server.createContext("/users/login", Controller::loginUser);
        server.createContext("/page1",Controller::page);
        server.createContext("/page2",Controller::page);
        server.createContext("/page3",Controller::page);

        //API REST
        server.createContext("/users", ApiRestController::users);
        server.start();
    }

    private static void loadDefaultUsers(){
        List<UserLogin> users = new ArrayList<>();
        List<Roles> roleAdmin = new ArrayList();
        roleAdmin.add(Roles.ADMIN);
        List<Roles> role1 = new ArrayList();
        role1.add(Roles.ROLE1);
        List<Roles> role2 = new ArrayList();
        role2.add(Roles.ROLE2);
        List<Roles> role3 = new ArrayList();
        role3.add(Roles.ROLE3);
        users.add(new UserLogin("Admin","Admin",roleAdmin));
        users.add(new UserLogin("user1","user1", role1));
        users.add(new UserLogin("user2","user2", role2));
        users.add(new UserLogin("user3","user3", role3));
        Factory.getRepositoryInstance().loadUsers(users);
    }
}
