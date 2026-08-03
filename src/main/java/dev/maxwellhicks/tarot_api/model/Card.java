package dev.maxwellhicks.tarot_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
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

    @Column(length = 1000)
    private String uprightMeaning;

    @Column(length = 1000)
    private String reversedMeaning;

    private String imageUrl;


}
