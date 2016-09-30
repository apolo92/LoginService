package com.test.web.server;

import com.sun.net.httpserver.HttpServer;
import com.test.web.domain.Controller;
import com.test.web.domain.factory.FactoryRepository;
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
        server.createContext("/", Controller::loginPage);
        server.createContext("/users/login", Controller::loginUser);
        server.createContext("/page1",Controller::page);
        server.createContext("/page2",Controller::page);
        server.createContext("/page3",Controller::page);
        server.start();
    }

    private static void loadDefaultUsers(){
        List<UserLogin> users = new ArrayList<>();
        users.add(new UserLogin("user1","user1", Roles.ROLE1));
        users.add(new UserLogin("user2","user2", Roles.ROLE2));
        users.add(new UserLogin("user3","user3", Roles.ROLE3));
        FactoryRepository.getRepositoryInstance().loadUsers(users);
    }
}
