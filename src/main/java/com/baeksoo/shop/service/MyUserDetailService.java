package com.baeksoo.shop.service;

import com.baeksoo.shop.custom.CustomUser;
import com.baeksoo.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = userRepository.findByEmail(username);

        List<GrantedAuthority> gl = new ArrayList<>();
        gl.add(new SimpleGrantedAuthority("User"));

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        var user = result.get();
//        System.out.println(user.getEmail()); // test11@test.com (성공)
//        System.out.println(user.getPassword()); // $2a$10$Jn5zkgiYJqfOxuuaRI.tlukZvngBvysRqp6D7vLQbF5yL9Hf2JXZu (성공)
        CustomUser customUser = new CustomUser(user.getEmail(), user.getPassword(), gl);
        customUser.id = user.getUser_id();
        customUser.displayName = "익명의 방문자";

        return customUser;
    }


}

