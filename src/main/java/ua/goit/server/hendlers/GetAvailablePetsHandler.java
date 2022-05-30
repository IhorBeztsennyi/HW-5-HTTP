package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.*;
import ua.goit.service.PetService;

import java.util.*;
import java.util.stream.Collectors;

public class GetAvailablePetsHandler extends AbstractHandler {

    PetService service = PetService.getInstance();

    @Override
    protected String getTemplateName() {
        return "getPets";
    }

    @Override
    protected void get(HttpExchange exchange) {
        Pet newPet = service.createNewAvailablePet();
        service.addANewPet(newPet);
        List<Pet> pets = service.getAvailablePets();
        String rowTemplates = pets.stream().map(pet -> {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", pet.getId().toString());
                    params.put("categoryId", pet.getCategory().getId().toString());
                    params.put("categoryName", pet.getCategory().getName());
                    params.put("petName", pet.getName());
                    params.put("status", pet.getStatus().getStatus());
                    return templateHandler.handleTemplate("table-row", params);
                })
                .collect(Collectors.joining());
        handleResponse(exchange, Map.of("tableRows", rowTemplates));
    }
}
