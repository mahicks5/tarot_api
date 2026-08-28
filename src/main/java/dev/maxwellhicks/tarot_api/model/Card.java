package dev.maxwellhicks.tarot_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArcanaType arcanaType;

    @Enumerated(EnumType.STRING)
    private Suit suit;

    private Integer number;

    @Column(columnDefinition = "TEXT")
    private String uprightMeaning;

    @Column(columnDefinition = "TEXT")
    private String reversedMeaning;

    private String imageUrl;
}
