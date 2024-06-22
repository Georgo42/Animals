# Animals Application - Exercise project

## Requirements
- Java 17 or higher
- PostgreSQL (tested on version 16.3)

## Setup

   1. Install PostgreSQL Server with default database "postgres" and set:
      - port: 5432 (default)
      - name: postgres
      - password: postgres
   2. Run PostgreSQL Server on localhost

## Original user story description (in Slovak language)
Zadanie Medior Java/Spring developer:

Naprogramujte jednoduchú backendovú aplikáciu s názvom "Animals", ktorá bude poskytovať základné CRUD operácie pre prácu so zvieratkami.

Aplikácia bude poskytovať 5 REST služieb:
1. addAnimal - REST metóda na pridanie nového zvieratka, prenosový objekt: AnimalDto
2. removeAnimal - REST metóda na zmazanie zvieratka, url parameter: id
3. getAnimal - REST metóda na načítanie zvieratka, url parameter: id, prenosový objekt: AnimalDto
4. updateAnimal - REST metóda na uloženie zmien zvieratka, prenosový objekt: AnimalDto
5. getAnimals - REST metóda na načítanie všetkých zvieratiek, prenosový objekt: AnimalDto[]
6. getAnimalsWithDetails - REST metóda na načítanie všetkých zvieratiek s detailnymi parametrami, prenosový objekt: AnimalWithDetailsDto[]

Prenosový objekt AnimalDto sa skladá z:
- long id;
- String name;
- int age;
- long breedId;
- String gender;

Prenosový objekt AnimalWithDetailsDto sa skladá z:
- long id;
- String name;
- int age;
- String breedName;
- String gender;

Poznámka:
- jednotlivé polia sú samopopisné, pričom
  - breedId je id z tabuľky BREED,
  - breadName je name z tabuľky BREAD na základe väzby breedId
  - gender je enumerácia o možnostiach MALE / FEMALE
- v servisných metódach je potrebné zapracovať aspoň základnú mieru validácie vstupných parametrov.

Databáza bude obsahovať 2 tabuľky:
1) ANIMAL so stĺpcami
- long id (primárny kĺúč)
- String name (not null, unikátne)
- int age (not null, age > 0)
- String gender (not null, JPA enum o možnostiach MALE / FEMALE)
- long breed_id (not null, foregin kĺúč do tabuľky BREED, ManyToOne asociácia)

2) BREED
- long id (primárny kĺúč)
- String name (not null, unikátne)

Tabuľka BREED bude mať nainicializované hodnoty:
1, "Afganský chrt"
2, "Americká akita"
3, "Anglický buldog"
4, "Belgický ovčiak"
5, "Bradáč"

Minimálne na 2 REST metódy vytvorte IT testy, ktoré overia ich základnú funkčnosť.
(logovanie, autentifikáciu nie je potrebné riešiť)

Výsledné zadanie je potrebné zbaliť do zip súboru a zaslať emailom. V prípade, že príloha prekročí veľkosť, zadanie môžete niekde uploadnúť a zaslať emailom link na stiahnutie, resp. použiť GitHub. Poprosím do riešenia spísať kroky, ktoré si bude potrebné aplikovať, aby sa aplikácia dala spustiť na mojom počítači.

Technológie, ktoré je potrebné použiť:
* Java 8 a vyššie
* Gradle alebo Maven
* Spring resp. Spring Boot
* Hibernate
* PostgreSQL, MySQL resp. iná relačná databáza
* jUnit

Voliteľné technológie
* flyway alebo liquibase
* jooq, QueryDSL, JPQL query, CriteriaAPI
* mockito

Hodnotí sa:
* Miera splnenia zadania
* Funkčnosť zadania
* Použité technológie, good practices, robusnosť, efektívnosť
* Prehľadnosť a dokumentovanie kódu
* Testovanie