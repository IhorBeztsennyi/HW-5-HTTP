package ua.goit.model;

import com.google.gson.Gson;

import java.net.http.HttpClient;

public class Requests {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Gson GSON = new Gson();



}
