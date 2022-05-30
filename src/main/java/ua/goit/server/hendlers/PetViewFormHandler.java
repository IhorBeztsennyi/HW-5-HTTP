package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.Pet;
import ua.goit.service.PetService;

import java.util.HashMap;
import java.util.Map;

public class PetViewFormHandler extends AbstractHandler {

    PetService service = new PetService();

    @Override
    protected String getTemplateName() {
        return "pet";
    }

    @Override
    protected void get(HttpExchange exchange) {
        Pet pet = service.createNewAvailablePet();
        Map<String, String> params = new HashMap<>();
        params.put("id", pet.getId().toString());
        params.put("categoryId", pet.getCategory().getId().toString());
        params.put("categoryName", pet.getCategory().getName());
        params.put("petName", pet.getName());
        params.put("status", pet.getStatus().toString());
        handleResponse(exchange, params);
    }
}
