package ua.goit;

import ua.goit.model.*;
import ua.goit.service.PetService;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        PetService service = PetService.getInstance();

        Pet newPet = service.createNewAvailablePet();

        Integer code = service.addANewPet(newPet);
        System.out.println(code);

        List<Pet> pets = service.findByStatus(StatusEnum.AVAILABLE);
        System.out.println("find by status" + pets);

        List<Pet> availablePets =  service.getAvailablePets();
        System.out.println("Find by status " + availablePets);

        Pet pet = service.findPetById(1L);
        System.out.println(pet);

        Integer code2 = service.updatePet( 1L, "kuzj", StatusEnum.SOLD);
        System.out.println(code2);
    }
}
