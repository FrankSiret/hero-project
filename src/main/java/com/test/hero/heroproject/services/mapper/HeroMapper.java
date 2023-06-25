package com.test.hero.heroproject.services.mapper;

import com.test.hero.heroproject.domain.Hero;
import com.test.hero.heroproject.services.dto.HeroDTO;

import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", imports = {Objects.class})
public interface HeroMapper extends EntityMapper<HeroDTO, Hero> {

    @Named("toHero")
    @Mapping(target = "name", expression = "java(Objects.toString(123))")
    HeroDTO toHero(Hero entity);

}
