package com.baeksoo.shop.service;

import com.baeksoo.shop.dto.UserDto;
import com.baeksoo.shop.entity.User;
import com.baeksoo.shop.repository.AuthRepository;
import com.baeksoo.shop.repository.UserRepository;
import com.baeksoo.shop.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //    유저 추가
    public void addUser(String email, String password) {

        if (!Validator.emailValidator(email)) {
            throw new IllegalArgumentException("not email format");
        }
        if (!Validator.passwordValidator(password)) {
            throw new IllegalArgumentException("not password format");
        }

        var encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        User user = new User();
        user.setUser(email, encodedPassword);

        userRepository.save(user);
    }
    // 단일 유저 조회
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new UserDto(user.getEmail());
    }
}
