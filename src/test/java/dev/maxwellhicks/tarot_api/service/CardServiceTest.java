package dev.maxwellhicks.tarot_api.service;

import dev.maxwellhicks.tarot_api.model.ArcanaType;
import dev.maxwellhicks.tarot_api.model.Card;
import dev.maxwellhicks.tarot_api.model.Suit;
import dev.maxwellhicks.tarot_api.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    void drawRandomCards_returnsCorrectNumberOfCards() {
        List<Card> fakeCards = List.of(
                new Card(1L, "The Fool", ArcanaType.MAJOR, null, 0, "up", "down", null),
                new Card(2L, "The Magician", ArcanaType.MAJOR, null, 1, "up", "down", null),
                new Card(3L, "Ace of Cups", ArcanaType.MINOR, Suit.CUPS, 1, "up", "down", null)
        );

        when(cardRepository.findAll()).thenReturn(fakeCards);

        List<Card> result = cardService.drawRandomCards(2);

        assertEquals(2, result.size());
    }
}
