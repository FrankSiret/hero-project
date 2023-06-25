package com.test.hero.heroproject.services;

import com.test.hero.heroproject.repository.HeroRepository;
import com.test.hero.heroproject.services.dto.HeroDTO;
import com.test.hero.heroproject.services.mapper.HeroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;

    private final HeroMapper heroMapper;

    HeroService(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    public Page<HeroDTO> findAll(Pageable pageable) {
        log.info("Request to get all Hero");
        return heroRepository.findAll(pageable).map(heroMapper::toDto);
    }
}
