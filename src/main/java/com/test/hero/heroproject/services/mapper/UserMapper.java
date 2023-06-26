package com.test.hero.heroproject.services.mapper;

import com.test.hero.heroproject.domain.Authority;
import com.test.hero.heroproject.domain.User;
import com.test.hero.heroproject.services.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
    default Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();
        if (authoritiesAsString != null) {
            authorities =
                    authoritiesAsString
                            .stream()
                            .map(string -> {
                                Authority auth = new Authority();
                                auth.setName(string);
                                return auth;
                            })
                            .collect(Collectors.toSet());
        }
        return authorities;
    }

    default Set<String> authoritiesToStrings(Set<Authority> authorities) {
        Set<String> authoritiesAsString = new HashSet<>();
        if (authorities != null) {
            authoritiesAsString =
                    authorities
                            .stream()
                            .map(Authority::getName)
                            .collect(Collectors.toSet());
        }
        return authoritiesAsString;
    }
}
