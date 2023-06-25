package com.test.hero.heroproject.domain;

import com.test.hero.heroproject.controller.HeroResourceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class HeroTest {

    private Hero hero1;
    private Hero hero2;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        hero1 = HeroResourceTest.createEntity(em);
        hero1.setId(HeroResourceTest.DEFAULT_ID);

        hero2 = HeroResourceTest.createEntity(em);
        hero2.setId(HeroResourceTest.DEFAULT_ID);
    }

    @Test
    void equalsVerifier() {
        assertThat(hero1).isEqualTo(hero2);

        assertThat(hero1).hasFieldOrPropertyWithValue("name", HeroResourceTest.DEFAULT_NAME);
        assertThat(hero1).hasFieldOrPropertyWithValue("superPower", HeroResourceTest.DEFAULT_SUPERPOWER);

        hero1.setId(HeroResourceTest.UPDATED_ID);
        assertThat(hero1).isNotEqualTo(hero2);
    }
}
