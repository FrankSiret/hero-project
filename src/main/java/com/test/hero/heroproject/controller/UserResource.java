package com.test.hero.heroproject.controller;

import com.test.hero.heroproject.services.UserService;
import com.test.hero.heroproject.services.dto.TokenDTO;
import com.test.hero.heroproject.services.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserVM userVM) {
        log.info("REST request to get user token login : {}", userVM.getUsername());
        TokenDTO tokenDTO = userService.login(userVM);
        return ResponseEntity.ok(tokenDTO);
    }

}
