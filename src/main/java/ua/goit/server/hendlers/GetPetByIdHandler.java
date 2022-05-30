package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.Pet;
import ua.goit.model.dto.PetIdDto;
import ua.goit.service.PetService;

import java.util.Collections;

public class GetPetByIdHandler extends AbstractHandler {

    private TemplateHandler templateHandler = TemplateHandler.getInstance();

    PetService service = new PetService();

    @Override
    protected String getTemplateName() {
        return "getPetById";
    }

    @Override
    protected void get(HttpExchange exchange) {

        handleResponse(exchange, Collections.emptyMap());
    }

    @Override
    protected void post(HttpExchange exchange) {
        PetIdDto petDto = getJsonRequestBody(exchange, PetIdDto.class);
        Pet pet = service.findPetById(petDto.getId());
        if (pet.getId() != null) {
            System.out.println(pet);
        } else {
            System.out.println("Pet with id " + pet.getId() + " is not found");
        }
    }
}
