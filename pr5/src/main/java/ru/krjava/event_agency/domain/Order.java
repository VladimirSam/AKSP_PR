package ru.krjava.event_agency.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;

    private Long userid;
}