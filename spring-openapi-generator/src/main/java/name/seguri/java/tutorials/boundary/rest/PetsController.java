package name.seguri.java.tutorials.boundary.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import name.seguri.java.tutorials.pets.PetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// You can override the mapping in application.yml
@RestController
@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
public class PetsController implements PetsApi {

  private final Random random = new Random();
  private final List<PetDto> pets = new ArrayList<>();

  @Override
  public ResponseEntity<List<PetDto>> listPets(Integer limit) {
    return ResponseEntity.ok(pets);
  }

  @Override
  public ResponseEntity<PetDto> showPetById(String petId) {
    var id = Long.valueOf(petId);
    var pet = pets.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElseThrow();
    return ResponseEntity.ok(pet);
  }

  @Override
  public ResponseEntity<Void> createPets() {
    var id = random.nextLong();
    var pet = new PetDto().id(id).name("pet-" + id).tag("tag-" + id);
    pets.add(pet);
    var location =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(location).build();
  }
}
