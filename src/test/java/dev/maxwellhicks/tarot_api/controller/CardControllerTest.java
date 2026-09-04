package dev.maxwellhicks.tarot_api.controller;

import dev.maxwellhicks.tarot_api.model.ArcanaType;
import dev.maxwellhicks.tarot_api.model.Card;
import dev.maxwellhicks.tarot_api.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CardService cardService;

    @Test
    void getAllCards_returnsListOfAllCards() throws Exception {
        List<Card> fakeCards = List.of(
                new Card(1L, "The Fool", ArcanaType.MAJOR, null, 0, "up", "down", null)
        );

        when(cardService.getAllCards()).thenReturn(fakeCards);

        mockMvc.perform(get("/api/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("The Fool"));
    }

    @Test
    void drawRandomCards_returnsRequestedCount() throws Exception {
        List<Card> fakeCards = List.of(
                new Card(1L, "The Fool", ArcanaType.MAJOR, null, 0, "up", "down", null),
                new Card(2L, "The Magician", ArcanaType.MAJOR, null, 1, "up", "down", null)
        );

        when(cardService.drawRandomCards(2)).thenReturn(fakeCards);

        mockMvc.perform(get("/api/cards/random?count=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void drawRandomCards_errorResponse() throws Exception {
        when(cardService.drawRandomCards(100)).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/api/cards/random?count=100"))
                .andExpect(status().isBadRequest());
    }
}