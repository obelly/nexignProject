package ru.nexign.crmservice.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthProvider implements AuthenticationProvider {
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var name = authentication.getName();
        var password = authentication.getCredentials();

        var user = userRepository.getUserByLogin(name);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + name + " не найден");
        }
        if (!user.getPassword().equals(password)){
            throw new BadCredentialsException("Неправильный пароль");
        }
        return new UsernamePasswordAuthenticationToken(user, password, Collections.singleton(user.getRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
