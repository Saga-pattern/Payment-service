package com.programing.service;

import com.programing.entity.User;
import com.programing.model.KafkaOrchestratorResponseDTO;
import com.programing.producer.KafkaProducer;
import com.programing.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @PostConstruct
    private void init() {
        userRepository.save(new User("Trong", 70000.0));
        userRepository.save(new User("Bao", 100000.0));
    }

    public List<User> getAll() {return userRepository.findAll();}

    @KafkaListener(topics = "${topic.name.payment.in}", groupId = "${spring.kafka.consumer.payment-group-id}")
    public void processPayment(KafkaOrchestratorResponseDTO orchestratorResponse) {
        User user = null;
        try {
            user = userRepository.findById(orchestratorResponse.getPaymentId()).get();
        } catch (Exception e) {
            kafkaProducer.sendCancelPaymen(orchestratorResponse);
            return;
        }

        if(!user.checkMoneyStock(orchestratorResponse.getPrice()*orchestratorResponse.getQuantity())){
            kafkaProducer.sendCancelPaymen(orchestratorResponse);
        } else {
            user.reduceMoneyStock(orchestratorResponse.getPrice()*orchestratorResponse.getQuantity());
            userRepository.save(user);

            kafkaProducer.sendPaymentProcessed(orchestratorResponse);
        }
    }
}
