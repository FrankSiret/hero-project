package com.test.hero.heroproject.services;

import com.test.hero.heroproject.domain.Authority;
import com.test.hero.heroproject.domain.User;
import com.test.hero.heroproject.exception.CustomException;
import com.test.hero.heroproject.repository.UserRepository;
import com.test.hero.heroproject.security.JwtTokenProvider;
import com.test.hero.heroproject.services.dto.TokenDTO;
import com.test.hero.heroproject.services.vm.UserVM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${security.jwt.token.expire-length}")
    private Long expiresIn;

    @Value("${security.jwt.token.type}")
    private String tokenType;

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    UserService(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public TokenDTO login(UserVM userVM) {
        String username = userVM.getUsername();
        String password = userVM.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception());
            String token = jwtTokenProvider.createToken(username, user.getAuthorities());
            return getTokenResponse(token);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (Exception e){
            throw new CustomException("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    protected TokenDTO getTokenResponse(String token){
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenType(tokenType);
        tokenDTO.setExpiresIn(expiresIn);
        tokenDTO.setAccessToken(token);
        return tokenDTO;
    }

}
