package com.programing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class KafkaOrchestratorResponseDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private Long productId;
    private Integer quantity;
    private Double price;
    private Long paymentId;
}
