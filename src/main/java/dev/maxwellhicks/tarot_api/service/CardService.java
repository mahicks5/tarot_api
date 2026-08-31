package dev.maxwellhicks.tarot_api.service;

import dev.maxwellhicks.tarot_api.model.Card;
import dev.maxwellhicks.tarot_api.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> drawRandomCards(int n) {
        List<Card> allCards = new ArrayList<>(cardRepository.findAll());

        if (n <= 0) {
            throw new IllegalArgumentException("Number of cards to draw must be positive.");
        }

        if (n > allCards.size()) {
            throw new IllegalArgumentException(
                    "Cannot draw " + n + " cards. Only " + allCards.size() + " cards exist."
            );
        }

        Collections.shuffle(allCards);
        return allCards.subList(0, n);
    }
}
