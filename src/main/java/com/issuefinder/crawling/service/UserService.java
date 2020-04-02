package com.issuefinder.crawling.service;

import com.issuefinder.crawling.config.security.JwtTokenProvider;
import com.issuefinder.crawling.exception.CustomException;
import com.issuefinder.crawling.mapper.UserMapper;
import com.issuefinder.crawling.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.PHONE_NUMBER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenProvider.createToken(email);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail());
        } else {
            throw new CustomException("email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User search(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public Boolean exist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username);
    }


    public void authsms() {
        Twilio.init("AC69ad7312f80cc97aa8e5454fe2b28896", "1e02e51e1e9f9379e76d6b876ae0468c");
        Message message = Message.creator(
                new PhoneNumber("+821022234800"),
                new PhoneNumber("+82102223"),
                "Sample Twilio SMS using Java")
                .create();

        System.out.println(message);
    }
}

