package ua.goit.service;

import com.google.gson.reflect.TypeToken;
import ua.goit.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PetService {

    private static PetService instance;

    private static final String ADD_NEW_PET_URL = "https://petstore.swagger.io/v2/pet";
    private static final String FIND_PETS_BY_STATUS_URL = "https://petstore.swagger.io/v2/pet/findByStatus";
    private static final String FIND_PET_BY_ID_URL = "https://petstore.swagger.io/v2/pet";
    private static final String UPDATE_PET_URL = "https://petstore.swagger.io/v2/pet";

    PetRequests requests = new PetRequests();

    public PetService() {
    }

    public static PetService getInstance() {
        if (instance == null) {
            instance = new PetService();
        }
        return instance;
    }

    public ArrayList<Pet> getAvailablePets() {
        try {
            return requests.findByStatus(FIND_PETS_BY_STATUS_URL, StatusEnum.AVAILABLE);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<Pet>();
    }

    public ArrayList<Pet> getPendingPets() {
        try {
            return requests.findByStatus(FIND_PETS_BY_STATUS_URL, StatusEnum.PENDING);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<Pet> getSoldPets() {
        try {
            return requests.findByStatus(FIND_PETS_BY_STATUS_URL, StatusEnum.SOLD);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<Pet>();
    }

    public Integer addANewPet(Pet pet) {
        try {
            return requests.addANewPet(URI.create(ADD_NEW_PET_URL), pet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 403;
    }

    public Pet findPetById(Long id) {
        try {
            return requests.findPetById(FIND_PET_BY_ID_URL, id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updatePet(Long id, String updatedName, Enum<StatusEnum> status) {
        try {
            return requests.updatePet(UPDATE_PET_URL, id, updatedName, status);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 404;
    }

    public ArrayList<Pet> findByStatus(Enum<StatusEnum> status) throws IOException, InterruptedException {
        return requests.findByStatus(FIND_PETS_BY_STATUS_URL, status);
    }

    public Pet createNewAvailablePet() {
        Pet newPet = new Pet();
        newPet.setId(1L);
        newPet.setCategory(new Category(2, "Birds"));
        newPet.setName("Parrot red");
        newPet.setPhotoUrls(List.of("photo1", "photo2"));
        newPet.setTags(List.of(new Tag(1, "tag1"), new Tag(2, "tag2")));
        newPet.setStatus(StatusEnum.AVAILABLE);
        return newPet;
    }

    public Pet createNewPendingPet() {
        Pet newPet = new Pet();
        newPet.setId(100L);
        newPet.setCategory(new Category(3, "Dogs"));
        newPet.setName("Patron");
        newPet.setPhotoUrls(List.of("photo1", "photo2"));
        newPet.setTags(List.of(new Tag(1, "tag1"), new Tag(2, "tag2")));
        newPet.setStatus(StatusEnum.PENDING);
        return newPet;
    }

    public Pet createNewSoldPet() {
        Pet newPet = new Pet();
        newPet.setId(123L);
        newPet.setCategory(new Category(4, "Cats"));
        newPet.setName("Fluffy");
        newPet.setPhotoUrls(List.of("photo1", "photo2"));
        newPet.setTags(List.of(new Tag(1, "tag1"), new Tag(2, "tag2")));
        newPet.setStatus(StatusEnum.SOLD);
        return newPet;
    }
}
