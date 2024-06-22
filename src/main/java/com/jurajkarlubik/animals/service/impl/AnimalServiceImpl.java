package com.jurajkarlubik.animals.service.impl;

import com.jurajkarlubik.animals.dto.AnimalDto;
import com.jurajkarlubik.animals.dto.AnimalWithDetailsDto;
import com.jurajkarlubik.animals.entity.Animal;
import com.jurajkarlubik.animals.entity.Breed;
import com.jurajkarlubik.animals.repository.AnimalRepository;
import com.jurajkarlubik.animals.repository.BreedRepository;
import com.jurajkarlubik.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Override
    public AnimalDto addAnimal(AnimalDto animalDto) {
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        animal.setAge(animalDto.getAge());
        animal.setGender(Animal.Gender.valueOf(animalDto.getGender()));
        Breed breed = breedRepository.findById(animalDto.getBreedId()).orElseThrow();
        animal.setBreed(breed);
        animalRepository.save(animal);
        return convertToDto(animal);
    }

    @Override
    public void removeAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public AnimalDto getAnimal(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow();
        return convertToDto(animal);
    }

    @Override
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        Animal animal = animalRepository.findById(animalDto.getId()).orElseThrow();
        animal.setName(animalDto.getName());
        animal.setAge(animalDto.getAge());
        animal.setGender(Animal.Gender.valueOf(animalDto.getGender()));
        Breed breed = breedRepository.findById(animalDto.getBreedId()).orElseThrow();
        animal.setBreed(breed);
        animalRepository.save(animal);
        return convertToDto(animal);
    }

    @Override
    public List<AnimalDto> getAnimals() {
        return animalRepository.findAll().stream().map(AnimalServiceImpl::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return animalRepository.findAll().stream().map(AnimalServiceImpl::convertToDetailedDto).collect(Collectors.toList());
    }

    public static AnimalDto convertToDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setAge(animal.getAge());
        dto.setBreedId(animal.getBreed().getId());
        dto.setGender(animal.getGender().name());
        return dto;
    }

    public static AnimalWithDetailsDto convertToDetailedDto(Animal animal) {
        AnimalWithDetailsDto dto = new AnimalWithDetailsDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setAge(animal.getAge());
        dto.setBreedName(animal.getBreed().getName());
        dto.setGender(animal.getGender().name());
        return dto;
    }
}

