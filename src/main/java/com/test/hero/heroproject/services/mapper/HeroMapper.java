package com.test.hero.heroproject.services.mapper;

import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.services.dto.HeroDTO;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HeroMapper extends EntityMapper<HeroDTO, Hero> {
}
