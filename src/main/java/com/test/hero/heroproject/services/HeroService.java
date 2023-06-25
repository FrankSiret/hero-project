package com.test.hero.heroproject.services;

import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.repository.HeroRepository;
import com.test.hero.heroproject.services.dto.HeroDTO;
import com.test.hero.heroproject.services.mapper.HeroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
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

    public HeroDTO save(HeroDTO heroDTO) {
        log.info("Request to save Hero : {}", heroDTO);
        Hero hero = heroMapper.toEntity(heroDTO);
        hero = heroRepository.save(hero);
        return heroMapper.toDto(hero);
    }

    public List<HeroDTO> findAllByName(String name, Pageable pageable) {
        log.info("Request to find all Hero by name containst : {}", name);
        List<Hero> list = heroRepository.findAllByNameLikeIgnoreCase(name, pageable).getContent();
        return heroMapper.toDto(list);
    }
}
