package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8080), 0);

        server.createContext("/", (HttpExchange exchange) -> {

            String response =
                    "<html>" +
                    "<head><title>CI/CD Demo</title></head>" +
                    "<body>" +
                    "<h1>Hi from CI/CD Pipeline - Automatic Deployment!</h1>" +
                    "<h2>Application is running successfully.</h2>" +
                    "<p>Server: AWS EC2</p>" +
                    "<p>Container: Docker</p>" +
                    "<p>Deployment: Jenkins</p>" +
                    "</body>" +
                    "</html>";

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/html");

            exchange.sendResponseHeaders(
                    200, response.getBytes().length);

            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.close();
        });

        server.start();

        System.out.println(
                "Application started on port 8080");
    }
}
