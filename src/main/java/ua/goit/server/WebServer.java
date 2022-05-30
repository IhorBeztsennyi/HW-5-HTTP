package ua.goit.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.goit.server.hendlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

public class WebServer {

    private final Map<String, HttpHandler> handlers = Map.of(
            "/", new IndexHandler(),
            "/getPetById", new GetPetByIdHandler(),
            "/petUpdate", new PetUpdateHandler(),
            "/petCreate", new PetCreateHandler(),
            "/getAvailablePets", new GetAvailablePetsHandler(),
            "/getPendingPets", new GetPendingPetsHandler(),
            "/getSoldPets", new GetSoldPetsHandler()
    );

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 80), 0);
        handlers.forEach(server::createContext);
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer();
        server.start();
    }
}
