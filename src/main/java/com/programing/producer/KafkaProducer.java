package com.programing.producer;

import com.programing.model.KafkaOrchestratorResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaOrchestratorResponseDTO> kafkaTemplate;

    @Value("${topic.name.payment.out}")
    private String topicPaymentProcessed;
    @Value("${topic.name.payment.cancel}")
    private String topicCancelPayment;

    public void sendPaymentProcessed(KafkaOrchestratorResponseDTO payload) {
        log.info("Sending to orchestrator topic={}, payload={}", topicPaymentProcessed, payload);
        kafkaTemplate.send(topicPaymentProcessed, payload);
    }

    public void sendCancelPaymen(KafkaOrchestratorResponseDTO payload) {
        log.info("Sending to orchestrator topic={}, payload={}", topicCancelPayment, payload);
        kafkaTemplate.send(topicCancelPayment, payload);
    }


}
