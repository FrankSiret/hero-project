package com.test.hero.heroproject.controller;

import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.services.HeroService;
import com.test.hero.heroproject.services.dto.HeroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HeroResource {

    private final Logger log = LoggerFactory.getLogger(HeroResource.class);

    private final HeroService heroService;

    HeroResource(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<HeroDTO>> getAllHero(Pageable pageable) {
        log.info("REST request to get all Heroes");
        Page<HeroDTO> page = heroService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
}
