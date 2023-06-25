package com.test.hero.heroproject.controller;

import com.test.hero.heroproject.domain.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class HeroResourceTest {

    public static final Long DEFAULT_ID = 1L;
    public static final Long UPDATED_ID = 2L;

    public static final String DEFAULT_NAME = "Ant-Man";
    public static final String UPDATED_NAME = "Spider-Man";

    public static final String DEFAULT_SUPERPOWER = "Ability to shrink in size";
    public static final String UPDATED_SUPERPOWER = "Is a spider!!";

    @Autowired
    private EntityManager em;

    public static Hero createEntity(EntityManager em) {
        Hero hero = new Hero();
        hero.setName(DEFAULT_NAME);
        hero.setSuperPower(DEFAULT_SUPERPOWER);
        return hero;
    }

    @BeforeEach
    void setUp() {
    }
}
