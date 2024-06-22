package com.jurajkarlubik.animals;

import com.jurajkarlubik.animals.dto.AnimalDto;
import com.jurajkarlubik.animals.dto.AnimalWithDetailsDto;
import com.jurajkarlubik.animals.entity.Animal;
import com.jurajkarlubik.animals.repository.AnimalRepository;
import com.jurajkarlubik.animals.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimalsApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Order(1)
    void addAnimalTest() {
        jdbcTemplate.execute("TRUNCATE TABLE animal RESTART IDENTITY");

        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Rex");
        animalDto.setAge(5);
        animalDto.setGender(Animal.Gender.MALE.name());
        animalDto.setBreedId(1L);

        ResponseEntity<AnimalDto> responseEntity = restTemplate.postForEntity("/animals", animalDto, AnimalDto.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getBody().getName(), "Rex");
        assertEquals(responseEntity.getBody().getAge(), 5);
        assertEquals(responseEntity.getBody().getGender(), Animal.Gender.MALE.name());
        assertEquals(responseEntity.getBody().getBreedId(), 1L);
    }

    @Test
    @Order(2)
    void getAnimalTest() {
        Animal animal = new Animal();
        animal.setName("Bella");
        animal.setAge(3);
        animal.setGender(Animal.Gender.FEMALE);
        animal.setBreed(animalRepository.findById(1L).orElseThrow().getBreed());
        animalRepository.save(animal);

        ResponseEntity<AnimalDto> responseEntity = restTemplate.getForEntity("/animals/" + animal.getId(), AnimalDto.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getBody().getName(), "Bella");
        assertEquals(responseEntity.getBody().getAge(), 3);
        assertEquals(responseEntity.getBody().getGender(), Animal.Gender.FEMALE.name());
        assertEquals(responseEntity.getBody().getBreedId(), 1L);
    }

    @Test
    @Order(3)
    void getAnimalsTest() {
        ResponseEntity<List<AnimalDto>> responseEntity = restTemplate.exchange(
                "/animals",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getBody().size(), 2);
        assertEquals(responseEntity.getBody().toString(),
                "[AnimalDto(id=1, name=Rex, age=5, breedId=1, gender=MALE), "
                        + "AnimalDto(id=2, name=Bella, age=3, breedId=1, gender=FEMALE)]"
        );
    }

    @Test
    @Order(4)
    void getAnimalsWithDetailsTest() {
        ResponseEntity<List<AnimalWithDetailsDto>> responseEntity = restTemplate.exchange(
                "/animals/details",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AnimalWithDetailsDto>>() {
                }
        );
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getBody().size(), 2);
        assertEquals(responseEntity.getBody().toString(),
                "[AnimalWithDetailsDto(id=1, name=Rex, age=5, breedName=Afganský chrt, gender=MALE), "
                        + "AnimalWithDetailsDto(id=2, name=Bella, age=3, breedName=Afganský chrt, gender=FEMALE)]"
        );
    }

    @Test
    @Order(5)
    void updateAnimalTest() {
        Animal animal = animalRepository.findById(1L).orElseThrow();
        animal.setName("Beny");
        animal.setAge(10);
        ResponseEntity<AnimalDto> responseEntity =
                restTemplate.exchange(
                        "/animals",
                        HttpMethod.PUT,
                        new HttpEntity<>(AnimalServiceImpl.convertToDto(animal)),
                        AnimalDto.class
                );

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(responseEntity.getBody().getName(), "Beny");
        assertEquals(responseEntity.getBody().getAge(), 10);
        assertEquals(responseEntity.getBody().getGender(), Animal.Gender.MALE.name());
        assertEquals(responseEntity.getBody().getBreedId(), 1L);
    }

    @Test
    @Order(6)
    void removeAnimalTest() {
        assertTrue(animalRepository.findById(2L).isPresent());

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/animals/" + 2L, HttpMethod.DELETE, null, Void.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(animalRepository.findById(2L).isEmpty());
    }
}

