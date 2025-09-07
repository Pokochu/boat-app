package controller;

import com.simple.boat.model.Boat;
import com.simple.boat.service.BoatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/boats")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;

    @GetMapping
    public List<Boat> getAllBoats(){
        return boatService.findAll();
    }

    @GetMapping("/{id}")
    public Boat getBoat(@PathVariable Long id) {
        return boatService.findById(id);
    }

    @PostMapping
    public Boat addBoat(@Valid @RequestBody Boat boat) {
        return boatService.save(boat);
    }

    @PutMapping("/{id}")
    public Boat updateBoat(@PathVariable Long id, @Valid @RequestBody Boat boat) {
        return boatService.update(id, boat);
    }

    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable Long id) {
        boatService.delete(id);
    }
}
