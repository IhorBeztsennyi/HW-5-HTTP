package ua.goit.server.hendlers;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.Pet;
import ua.goit.model.dto.PetConverter;
import ua.goit.model.dto.PetDto;
import ua.goit.service.PetService;

import java.util.Collections;

public class PetCreateHandler extends AbstractHandler {
    PetService service = new PetService();
    PetConverter converter = new PetConverter();

    @Override
    protected String getTemplateName() {
        return "petCreate";
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange, Collections.emptyMap());
    }

    @Override
    protected void post(HttpExchange exchange) {
        PetDto petDto = getJsonRequestBody(exchange, PetDto.class);
        Pet newPet = converter.daoToDto(petDto);
        Integer code = service.addANewPet(newPet);
        System.out.println("Pet creation code: " + code + " " + newPet);

    }
}
