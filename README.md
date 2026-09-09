# Tarot API

### Description
A RESTful Tarot card API built with Spring Boot.

Supports retrieving the full 78-card deck, drawing a single random card, or drawing any number of random cards at once.

### Features
- Full deck retrieval - fetch all 78 cards
- Random draws - pull 1 or more unique random cards in a single request.
- Input validation - clear, correct HTTP status codes for invalid requests (like when requesting more cards that exist > 78)
- Automated tests - unit tests for business logic, integration tests for the HTTP layer

### Tech Stack
- Java 17
- Spring Boot 4.1 (Spring Web MVC, Spring Data JPA)
- PostGreSQL - persistent storage for the card deck
- Hibernate - ORM layer, mapping
- Springdoc-openapi - auto generated OpenAPI 3.1 spec + Swagger UI
- JUnit 5 + Mockito - unit and integration testing
- Lombok - boilerplate reduction (getters, setters, and constructors)
- Maven - build and dependency

### Architecture
This project follows a standard layered architecture, separating concerns so each piece is independently testable and reusable

Controller -> Service -> Repository -> Database -> (HTTP Layer) (Business logic) (Data access)

### API Endpoints
| Method | Endpoints           | Description                   |
|--------|---------------------|-------------------------------|
| GET    | /api/cards          | Returns all 78 cards          |
| GET    | /api/random         | Returns 1 random card         |
| GET    | /api/random?count=n | Returns n unique random cards |

### Getting Started
Prerequisites
- Java 17+
- Maven (or use the included ``mvnw`` wrapper)
- PostgreSQL, running locally

### Setup
1. Clone the repo
```bash
git clone https://github.com/mahicks5/tarot_api.git
cd tarot_api
```

2. Create the database
```bash
create db
```

3. Configure your local credentials
```properties
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

4. Run the application
```bash
./mvnw spring-boot:run
```

5. Try it out!
- API base URL: ``http://localhost:8080/api/cards``
- Swagger docs: ``http://localhost:8080/swagger-ui.html``

### Running Tests
```bash
./mvnw test
```

Test Coverage Includes:
- ``CardServiceTest`` - validates draw logic, empty/negative/over-limit boundary conditions, using Mockito to isolate business logic from the database
- ``CardControllerTest`` - validates HTTP status codes, JSON response shape, and end-to-end error handling using ``MockMvc``

### Data Model
Each card includes
- ``name`` - e.g. "The Fool", "Ace of Cups"
- ``arcanaType`` - ``MAJOR`` or ``MINOR``
- ``suit`` - ``WANDS``, ``CUPS``, ``SWORDS``, or ``PENTACLES`` (null for Major Arcana)
- ``number`` - 0-21 for Major Arcana, 1–14 for Minor Arcana
- ``uprightMeaning`` / ``reversedMeaning`` - detailed card interpretations
- ``imageUrl`` - reserved for future card artwork support

### Design Decisions
A few notable choices made along the way, and the reasoning behind them:
- Single entity vs. class hierarchy - considered modeling Major/Minor Arcana as separate classes with JPA inheritance, but chose a single Card entity with nullable fields instead.
  - For a small, fixed, 78-row dataset with no behavioral differences between card types, inheritance (and the SQL joins or discriminator columns it requires) would add complexity without benefit.
- ``EnumType.STRING`` over ``EnumType.ORDINAL`` - enums are stored as readable strings in the database rather than numeric positions. This avoids data corruption if enum values are ever reordered or added.
- Shuffle-and-slice over rejection sampling - random draws work by shuffling the full card list once and taking the first n, rather than repeatedly picking random cards and checking for duplicates.
  - This guarantees uniqueness by construction and avoids performance degradation as ``n`` approaches the full deck size.
- ``/api/cards/random?count=n`` over separate endpoints - a single random-draw endpoint handles both "one card" and "n cards" requests, since both cases share identical logic and shape (an array response), keeping the API's contract simple and predictable.
- Config split for secrets - database credentials live in a ``git-ignored application-local.properties`` file, kept separate from the committed ``application.properties``, so the repository can be public without exposing secrets.

### Future Improvements
- Card artwork using the ``imageUrl`` property
- Pagination on ``/api/cards``
- Filtering by arcana type or suit
- Dockerized setup for one-command local development