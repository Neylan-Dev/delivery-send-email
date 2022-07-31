package com.neylandev.delivery.application.route;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import com.neylandev.delivery.domain.service.DeliverySendEmailService;
import com.neylandev.delivery.domain.utils.Constants;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("{{from.delivery.email}}")
                .id(Constants.DIRECT_SEND_EMAIL)
                .unmarshal()
                .json(JsonLibrary.Jackson, DeliveryEmailDto.class)
                .bean(DeliverySendEmailService.class);

    }
}
