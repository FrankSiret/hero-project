package com.test.hero.heroproject.repository;

import com.test.hero.heroproject.domain.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    Page<Hero> findAllByNameLikeIgnoreCase(String name, Pageable pageable);
}
