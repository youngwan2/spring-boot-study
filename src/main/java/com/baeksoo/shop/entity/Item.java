package com.baeksoo.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String price;

    // 기본 생성자
    public Item(){ }

    // 커스텀 생성자
    public Item(String name, String price) {
        this.title = name;
        this.price = price;
    }
}
