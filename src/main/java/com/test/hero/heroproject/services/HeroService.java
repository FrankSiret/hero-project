package com.test.hero.heroproject.services;

import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.repository.HeroRepository;
import com.test.hero.heroproject.services.dto.HeroDTO;
import com.test.hero.heroproject.services.mapper.HeroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;

    private final HeroMapper heroMapper;

    private CacheManager cacheManager;

    HeroService(HeroRepository heroRepository, HeroMapper heroMapper, CacheManager cacheManager) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
        this.cacheManager = cacheManager;
    }

    public Page<HeroDTO> findAll(Pageable pageable) {
        log.info("Request to get all Hero");
        return heroRepository.findAll(pageable).map(heroMapper::toDto);
    }

    public HeroDTO save(HeroDTO heroDTO) {
        log.info("Request to save Hero : {}", heroDTO);
        Hero hero = heroMapper.toEntity(heroDTO);
        hero = heroRepository.save(hero);
        clearCache(hero.getId());
        return heroMapper.toDto(hero);
    }

    public List<HeroDTO> findAllByName(String name, Pageable pageable) {
        log.info("Request to find all Hero by name contains : {}", name);
        List<Hero> list = heroRepository.findByNameContainsIgnoreCase(name, pageable);
        return heroMapper.toDto(list);
    }

    @Cacheable("heroes")
    public Optional<HeroDTO> findById(Long id) {
        log.info("Request to find a Hero by id : {}", id);
        Optional<Hero> hero = heroRepository.findById(id);
        return hero.map(heroMapper::toDto);
    }

    public void delete(Long id) {
        log.info("Request to delete Hero : {}", id);
        heroRepository.deleteById(id);
        clearCache(id);
    }

    private void clearCache(Long id) {
        Objects.requireNonNull(cacheManager.getCache("heroes")).evict(id);
    }
}
