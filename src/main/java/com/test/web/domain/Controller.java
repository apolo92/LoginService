package com.test.web.domain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.test.web.domain.utils.ReadResoures;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by alejandro on 28/09/2016.
 */
public class Controller {

    public static void login(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(ReadResoures.readResource("login.html"));
        os.close();
    }

    public static void page(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(ReadResoures.readResource("page.html"));
        os.close();
    }
}
