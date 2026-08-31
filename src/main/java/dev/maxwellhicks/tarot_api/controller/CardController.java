package dev.maxwellhicks.tarot_api.controller;

import dev.maxwellhicks.tarot_api.model.Card;
import dev.maxwellhicks.tarot_api.service.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/random")
    public List<Card> getRandomCards(@RequestParam(defaultValue = "1") int count) {
        return cardService.drawRandomCards(count);
    }
}