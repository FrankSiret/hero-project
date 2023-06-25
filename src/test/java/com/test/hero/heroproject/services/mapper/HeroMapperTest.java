package com.test.hero.heroproject.services.mapper;

import com.test.hero.heroproject.controller.HeroResourceTest;
import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.services.HeroService;
import com.test.hero.heroproject.services.dto.HeroDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HeroMapperTest {

    private Hero hero;

    private HeroDTO heroDTO;

    private HeroMapper heroMapper;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        heroMapper = new HeroMapperImpl();
    }

    @Test
    void shouldMapperToDto() {
        hero = HeroResourceTest.createEntity(em);
        hero.setId(HeroResourceTest.DEFAULT_ID);

        heroDTO = heroMapper.toDto(hero);

        assertThat(heroDTO).hasFieldOrPropertyWithValue("id", HeroResourceTest.DEFAULT_ID);
        assertThat(heroDTO).hasFieldOrPropertyWithValue("name", HeroResourceTest.DEFAULT_NAME);
        assertThat(heroDTO).hasFieldOrPropertyWithValue("superPower", HeroResourceTest.DEFAULT_SUPERPOWER);
    }
}
