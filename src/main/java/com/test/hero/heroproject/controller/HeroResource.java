package com.test.hero.heroproject.controller;

import com.test.hero.heroproject.config.TimingRequest;
import com.test.hero.heroproject.controller.errors.BadRequestAlertException;
import com.test.hero.heroproject.repository.HeroRepository;
import com.test.hero.heroproject.services.HeroService;
import com.test.hero.heroproject.services.dto.HeroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HeroResource {

    private final Logger log = LoggerFactory.getLogger(HeroResource.class);

    private static final String ENTITY_NAME = "hero";

    private final HeroService heroService;

    private final HeroRepository heroRepository;

    HeroResource(HeroService heroService, HeroRepository heroRepository) {
        this.heroService = heroService;
        this.heroRepository = heroRepository;
    }

    @TimingRequest
    @GetMapping("/heroes")
    public ResponseEntity<List<HeroDTO>> getAllHero(Pageable pageable) {
        log.info("REST request to get all Heroes");
        Page<HeroDTO> page = heroService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @TimingRequest
    @GetMapping("/heroes/{id}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable Long id) {
        log.info("REST request to get a Hero by id : {}", id);
        Optional<HeroDTO> hero = heroService.findById(id);
        if(hero.isPresent()) {
            return ResponseEntity.ok().body(hero.get());
        }
        return ResponseEntity.notFound().build();
    }

    @TimingRequest
    @GetMapping("/heroes/by-name/{name}")
    public ResponseEntity<List<HeroDTO>> getAllHeroByName(@PathVariable String name, Pageable pageable) {
        log.info("REST request to get all Heroes where name contains : {}", name);
        List<HeroDTO> page = heroService.findAllByName(name, pageable);
        return ResponseEntity.ok().body(page);
    }

    @TimingRequest
    @PostMapping("/heroes")
    @PermitAll
    public ResponseEntity<HeroDTO> createHero(@RequestBody HeroDTO heroDTO) throws URISyntaxException {
        log.info("REST request to save Hero : {}", heroDTO);
        if(heroDTO.getId() != null) {
            throw new BadRequestAlertException("A new hero cannot already have an ID", ENTITY_NAME, "idexist");
        }
        HeroDTO result = heroService.save(heroDTO);
        return ResponseEntity
                .created(new URI("/api/heroes/" + result.getId()))
                .body(result);
    }

    @TimingRequest
    @PutMapping("/heroes/{id}")
    @PermitAll
    public ResponseEntity<HeroDTO> updateHero(@PathVariable Long id, @RequestBody HeroDTO heroDTO) throws URISyntaxException {
        log.info("REST request to update a Hero : {}, {}", id, heroDTO);
        if(heroDTO.getId() == null) {
            throw new BadRequestAlertException("A hero cannot have ID null", ENTITY_NAME, "idnull");
        }
        if(!heroDTO.getId().equals(id)) {
            throw new BadRequestAlertException("A hero id dismatch", ENTITY_NAME, "idvalid");
        }
        if(!heroRepository.existsById(id)) {
            throw new BadRequestAlertException("A hero not exist", ENTITY_NAME, "notexist");
        }
        HeroDTO result = heroService.save(heroDTO);
        return ResponseEntity.ok(result);
    }

    @TimingRequest
    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id) {
        log.info("REST request to delete Hero : {}", id);
        heroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
