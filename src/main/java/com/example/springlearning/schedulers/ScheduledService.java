package com.example.springlearning.schedulers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final JavaMailSender javaMailSender;

    //@Scheduled(fixedRate = 5 * 1000)
    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("aygunabasova7896@gmail.com");
        msg.setFrom("aygunabsva@gmail.com");
        msg.setSubject("spring boot learning");
        msg.setText("Hello, this is test mail");
        javaMailSender.send(msg);
    }

    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("aygunabasova7896@gmail.com");
        helper.setFrom("aygunabsva@gmail.com");
        helper.setSubject("spring boot learning");
        helper.setText("Hello, this is test mail");

        //helper.addAttachment("Mail Sending", new File("src/main/java/com/example/springlearning/text.txt"));
        helper.addAttachment("Mail Sending", new File("src/main/java/com/example/springlearning/test.jpg"));
        javaMailSender.send(msg);
    }
}
