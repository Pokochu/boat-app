package com.simple.boat.service;

import com.simple.boat.model.Boat;
import com.simple.boat.repository.BoatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoatService {

    private final BoatRepository repository;

    public List<Boat> findAll() {
        return repository.findAll();
    }

    public Boat findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Boat not found"));
    }

    public Boat save(Boat boat) {
        return repository.save(boat);
    }

    public Boat update(Long id, Boat boat) {
        Boat existing = findById(id);
        existing.setName(boat.getName());
        existing.setDescription(boat.getDescription());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
