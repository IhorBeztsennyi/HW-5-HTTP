package ua.goit.model.dto;

import ua.goit.model.Category;
import ua.goit.model.Pet;
import ua.goit.model.Tag;

import java.util.List;

public class PetConverter {

    public Pet daoToDto(PetDto type) {
        Pet pet = new Pet();
        pet.setId(type.getId());
        pet.setCategory(new Category(type.getCategoryId(), type.getCategoryName()));
        pet.setName(type.getPetName());
        pet.setPhotoUrls(List.of("photo1", "photo2"));
        pet.setTags(List.of(new Tag(1, "tag1"), new Tag(2, "tag2")));
        pet.setStatus(type.getStatus());
        return pet;
    }
}
