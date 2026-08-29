package dev.maxwellhicks.tarot_api.config;

import dev.maxwellhicks.tarot_api.model.Card;
import dev.maxwellhicks.tarot_api.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;


@Component
public class DataSeeder implements CommandLineRunner {
    private final CardRepository cardRepository;

    public DataSeeder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cardRepository.count() > 0) {
            System.out.println("Cards already loaded. Skipping seed step.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("data/cards.json").getInputStream();
        List<Card> cards = objectMapper.readValue(inputStream, new tools.jackson.core.type.TypeReference<List<Card>>() {});

        cardRepository.saveAll(cards);
        System.out.println("Loaded " +cards.size() + " cards into the database");
    }
}
