package com.sofka.transactions.infrastructure.adapter.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaProducer {

    private static final String TOPIC = "cliente-cuenta-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void senCuentaCreated(String numeroCuenta, Long idCliente) throws JsonProcessingException {
        Map<String, Object> data = Map.of(
                "numeroCuenta", numeroCuenta,
                "idCliente", idCliente
        );
        sendMessage(new ObjectMapper().writeValueAsString(data));
    }
}

