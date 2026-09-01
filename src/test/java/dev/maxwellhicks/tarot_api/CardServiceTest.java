package dev.maxwellhicks.tarot_api;

import dev.maxwellhicks.tarot_api.repository.CardRepository;
import dev.maxwellhicks.tarot_api.service.CardService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;
}
