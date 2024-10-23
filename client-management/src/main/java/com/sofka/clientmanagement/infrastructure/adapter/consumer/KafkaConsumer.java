package com.sofka.clientmanagement.infrastructure.adapter.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.clientmanagement.application.service.ClienteService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class KafkaConsumer {
    private static final String TOPIC = "cliente-cuenta-topic";
    private static final Logger logger = Logger.getLogger(KafkaConsumer.class.getName());

    private final ClienteService clienteService;

    public KafkaConsumer(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String message) {
        Map<String, Object> data = null;
        try {
            data = new ObjectMapper().readValue(message,  new TypeReference<Map<String, Object>>() {});
            clienteService.addCuentaCliente((String) data.get("numeroCuenta"),  ((Integer) data.get("idCliente")).longValue());
        } catch (JsonProcessingException e) {
            logger.warning("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}

