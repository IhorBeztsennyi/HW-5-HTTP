package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.Pet;
import ua.goit.model.dto.PetConverter;
import ua.goit.model.dto.PetUpdateDto;
import ua.goit.service.PetService;

import java.util.Collections;

public class PetUpdateHandler extends AbstractHandler {
    PetService service = new PetService();

    @Override
    protected String getTemplateName() {
        return "petUpdate";
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange, Collections.emptyMap());
    }

    @Override
    protected void post(HttpExchange exchange) {
        service.addANewPet(service.createNewAvailablePet());
        PetUpdateDto petDto = getJsonRequestBody(exchange, PetUpdateDto.class);
        Pet existingPet = service.findPetById(petDto.getId());
        if (existingPet != null && existingPet.getId() != null) {
            Integer code = service.updatePet(existingPet.getId(), existingPet.getName(), existingPet.getStatus());
            System.out.println("Pet with id " + existingPet.getId() + " was updated with code " + code);
        } else {
            System.out.println("Pet with id " + petDto.getId() + " was not found");
        }
    }
}
