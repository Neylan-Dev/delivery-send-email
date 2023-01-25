package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import com.neylandev.delivery.domain.enums.StatusEmail;
import com.neylandev.delivery.domain.model.Email;
import com.neylandev.delivery.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Handler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DeliverySendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${send.email.from}")
    private String fromEmail;

    @Handler
    @Transactional
    public void sendEmail(DeliveryEmailDto deliveryEmailDto) {

        Email email = modelMapper.map(deliveryEmailDto, Email.class);
        email.setStatusEmail(StatusEmail.SENT);

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(deliveryEmailDto.getClientEmail());
            simpleMailMessage.setSubject(deliveryEmailDto.getSubject());
            simpleMailMessage.setText(deliveryEmailDto.getBody());

            email.setStatusEmail(StatusEmail.SENT);
            email.setSendDateEmail(LocalDateTime.now());

            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }
}
