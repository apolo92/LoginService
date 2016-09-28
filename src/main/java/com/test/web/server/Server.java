package com.test.web.server;

import com.sun.net.httpserver.HttpServer;
import com.test.web.domain.Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        server.createContext("/", Controller::login);
        server.createContext("/page1",Controller::page);
        server.createContext("/page2",Controller::page);
        server.createContext("/page3",Controller::page);
        server.start();
    }
}
