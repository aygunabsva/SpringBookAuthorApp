package com.example.springlearning.service;

import com.example.springlearning.dto.ExceptionDTO;
import com.example.springlearning.dto.LoginReq;
import com.example.springlearning.dto.LoginRes;
import com.example.springlearning.dto.UserReqDto;
import com.example.springlearning.entity.User;
import com.example.springlearning.repository.UserRepository;
import com.example.springlearning.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public ResponseEntity<?> authenticate(LoginReq loginReq){
        log.info("authenticate method started by: {}", loginReq.getUsername());
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(),
                            loginReq.getPassword()));
            log.info("authentication details: {}", authentication);
            String username = authentication.getName();
            User client = new User(username,"");
            String token = jwtUtil.createToken(client);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            LoginRes loginRes = new LoginRes(username,token);
            log.info("user: {} logged in",  client.getUsername());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginRes);

        }catch (BadCredentialsException e){
            ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),"Invalid username or password");
            log.error("Error due to {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
        }catch (Exception e){
            ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            log.error("Error due to {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
        }
    }

    public void register(UserReqDto userReqDto){
        User user = new User();
        user.setId(userReqDto.getId());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setName(userReqDto.getName());
        user.setAuthorities(userReqDto.getAuthorities());
        user.setStatus(userReqDto.getStatus());
        userRepository.save(user);
        log.info("user registered -> {}", user.getUsername());
    }
}
