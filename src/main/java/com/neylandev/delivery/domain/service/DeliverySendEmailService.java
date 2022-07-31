package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliverySendEmailService {

    public void sendEmail(DeliveryEmailDto deliveryEmailDto){
        //TODO send to smtp service
    }
}
