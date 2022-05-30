package ua.goit.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PetRequests {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Gson GSON = new Gson();

    public PetRequests() {
    }

    public Integer uploadImage(String url1, int id, String image) throws IOException, InterruptedException {
        URL url = new URL(url1 + "/" + id + "/uploadImage");
        final String requestBody = "file=@" + image + ";type=image/jpeg";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "multipart/form-data")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public Integer addANewPet(URI uri, Pet pet) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public ArrayList<Pet> findByStatus(String url, Enum<StatusEnum> status) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?status=" + status))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        ArrayList<Pet> pets = GSON.fromJson(response.body(), new TypeToken<List<Pet>>() {
        }.getType());
        return pets;
    }

    public Pet findPetById(String url, Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), Pet.class);
    }

    public Integer updatePet(String url, Long id, String updatedName, Enum<StatusEnum> status) throws IOException, InterruptedException {
        final String requestBody = "name=" + updatedName + "&status=" + status;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
}
