package org.example.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String msg = "Hello from "+Thread.currentThread().getName();
        httpExchange.sendResponseHeaders(200,msg.length());
//        httpExchange.getRequestBody();
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(msg.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
