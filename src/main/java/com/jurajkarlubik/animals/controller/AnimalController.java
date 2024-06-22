package com.jurajkarlubik.animals.controller;

import com.jurajkarlubik.animals.dto.AnimalDto;
import com.jurajkarlubik.animals.dto.AnimalWithDetailsDto;
import com.jurajkarlubik.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDto> addAnimal(@RequestBody @Valid AnimalDto animalDto) {
        return ResponseEntity.ok(animalService.addAnimal(animalDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAnimal(@PathVariable Long id) {
        animalService.removeAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimal(id));
    }

    @PutMapping
    public ResponseEntity<AnimalDto> updateAnimal(@RequestBody @Valid AnimalDto animalDto) {
        return ResponseEntity.ok(animalService.updateAnimal(animalDto));
    }

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAnimals() {
        return ResponseEntity.ok(animalService.getAnimals());
    }

    @GetMapping("/details")
    public ResponseEntity<List<AnimalWithDetailsDto>> getAnimalsWithDetails() {
        return ResponseEntity.ok(animalService.getAnimalsWithDetails());
    }
}

