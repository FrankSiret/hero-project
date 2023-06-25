package com.test.hero.heroproject.controller;

import com.test.hero.heroproject.HeroProjectApplication;
import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.repository.HeroRepository;
import com.test.hero.heroproject.services.dto.HeroDTO;
import com.test.hero.heroproject.services.mapper.HeroMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WithMockUser
@SpringBootTest(classes = {HeroProjectApplication.class})
public class HeroResourceTest {

    public static final Long DEFAULT_ID = 1L;
    public static final Long UPDATED_ID = 2L;

    public static final String DEFAULT_NAME = "Ant-Man";
    public static final String UPDATED_NAME = "Spider-Man";

    public static final String DEFAULT_SUPERPOWER = "Ability to shrink in size";
    public static final String UPDATED_SUPERPOWER = "Is a spider!!";

    private static final String ENTITY_API_URL = "/api/heroes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private EntityManager em;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroMapper heroMapper;

    @Autowired
    private MockMvc restHeroMockMvc;

    Hero hero;

    public static Hero createEntity(EntityManager em) {
        Hero hero = new Hero();
        hero.setName(DEFAULT_NAME);
        hero.setSuperPower(DEFAULT_SUPERPOWER);
        return hero;
    }

    @BeforeEach
    void setUp() {
        hero = createEntity(em);
    }

    @Test
    @Transactional
    void createHero() throws Exception {
        int databaseSizeBeforeCreate = heroRepository.findAll().size();
        HeroDTO heroDTO = heroMapper.toDto(hero);
        restHeroMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(heroDTO)))
                .andExpect(status().isCreated());

        List<Hero> heroList = heroRepository.findAll();
        assertThat(heroList).hasSize(databaseSizeBeforeCreate + 1);
        Hero testHero = heroList.get(heroList.size() - 1);
        assertThat(testHero.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHero.getSuperPower()).isEqualTo(DEFAULT_SUPERPOWER);
    }

    @Test
    @Transactional
    void updateHero() throws Exception {

        heroRepository.saveAndFlush(hero);

        int databaseSizeBeforeUpdate = heroRepository.findAll().size();

        HeroDTO heroDTO = heroMapper.toDto(hero);
        heroDTO.setSuperPower(UPDATED_SUPERPOWER);

        restHeroMockMvc
                .perform(put(ENTITY_API_URL_ID, heroDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(heroDTO)))
                .andExpect(status().isOk());

        List<Hero> heroList = heroRepository.findAll();
        assertThat(heroList).hasSize(databaseSizeBeforeUpdate);

        Hero testHero = heroList.get(heroList.size() - 1);
        assertThat(testHero.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHero.getSuperPower()).isEqualTo(UPDATED_SUPERPOWER);
    }


}
