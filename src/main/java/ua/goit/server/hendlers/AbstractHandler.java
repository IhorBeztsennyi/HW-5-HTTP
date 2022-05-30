package ua.goit.server.hendlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

abstract public class AbstractHandler implements HttpHandler {

    protected TemplateHandler templateHandler = TemplateHandler.getInstance();
    protected Gson jsonParser = new Gson();

    protected String getTemplateName() {
        return null;
    }

    protected void get(HttpExchange exchange) {
    }

    ;

    protected void post(HttpExchange exchange) throws IOException {
    }

    ;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                get(exchange);
                break;
            case "POST":
                post(exchange);
                break;
        }
    }

    protected Map<String, String> getUrlParams(HttpExchange exchange) {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        if (query == null) {
            return Collections.emptyMap();
        }
        return Arrays.stream(query.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(k -> k[0], v -> v[1]));
    }

    protected <T> T getJsonRequestBody(HttpExchange exchange, Class<T> classType) {
        try (InputStream inputStream = exchange.getRequestBody();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String jsonString = scanner.nextLine();
            return jsonParser.fromJson(jsonString, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void handleResponse(HttpExchange exchange) {
        handleResponse(exchange, Collections.emptyMap());
    }

    protected void handleResponse(HttpExchange exchange, Map<String, String> params) {
        String body = templateHandler.handleTemplate(getTemplateName(), params);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, body.length());
            outputStream.write(body.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
