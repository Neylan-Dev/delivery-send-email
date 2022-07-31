package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliverySendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${send.email.from}")
    private String fromEmail;

    @Handler
    public void sendEmail(DeliveryEmailDto deliveryEmailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(deliveryEmailDto.getClientEmail());
        simpleMailMessage.setSubject(deliveryEmailDto.getSubject());
        simpleMailMessage.setText(deliveryEmailDto.getBody());
        javaMailSender.send(simpleMailMessage);
    }
}
