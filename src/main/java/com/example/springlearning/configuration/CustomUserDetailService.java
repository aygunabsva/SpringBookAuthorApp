package com.example.springlearning.configuration;

import com.example.springlearning.entity.Authority;
import com.example.springlearning.entity.User;
import com.example.springlearning.enums.UserStatus;
import com.example.springlearning.exception.NotFoundException;
import com.example.springlearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

//    private CustomSpringSecurityUser customSpringSecurityUser(User user) {
//        checkUserProfileStatus(user);
//
//
////        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
////                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
////                .collect(Collectors.toList());
////        return new CustomSpringSecurityUser(user.getName(), user.getUsername(), grantedAuthorities);
//    }

    private void checkUserProfileStatus(User user) {
        if (user.getStatus() != UserStatus.ACTIVE){
            throw new NotFoundException("Username is not Active");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("user not found"));
        checkUserProfileStatus(user);
        Set<Authority> authoritySet = user.getAuthorities();
//        List<String> roles = new ArrayList<>();
//        for (Authority authority : authoritySet) {
//            roles.add(authority.getName());
//        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(String.valueOf(authoritySet))
                .build();



//        return userRepository.findByUsername(username)
//                .map(this::customSpringSecurityUser)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
