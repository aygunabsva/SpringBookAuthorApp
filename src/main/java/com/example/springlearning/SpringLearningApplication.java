package com.example.springlearning;

import com.example.springlearning.schedulers.ScheduledService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringLearningApplication {

    private final ScheduledService scheduledService;
    public static void main(String[] args) {
        SpringApplication.run(SpringLearningApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        scheduledService.sendEmail();
//    }



}