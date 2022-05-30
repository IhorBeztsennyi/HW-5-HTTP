package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.Pet;
import ua.goit.service.PetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetSoldPetsHandler extends AbstractHandler {
    PetService service = PetService.getInstance();

    @Override
    protected String getTemplateName() {
        return "getPets";
    }

    @Override
    protected void get(HttpExchange exchange) {
        Pet newPet = service.createNewSoldPet();
        service.addANewPet(newPet);
        List<Pet> pets = service.getSoldPets();
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
