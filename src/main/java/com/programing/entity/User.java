package com.programing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_user")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double moneyStock;

    public User(String name, Double moneyStock) {
        this.name = name;
        this.moneyStock = moneyStock;
    }

    public boolean checkMoneyStock(Double totalPrice) {
        if(moneyStock>=totalPrice) return true;

        return false;
    }

    public void reduceMoneyStock(Double totalPrice) {
        this.moneyStock -= totalPrice;
    }
}
