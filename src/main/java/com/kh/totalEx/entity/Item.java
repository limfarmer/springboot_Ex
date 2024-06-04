package com.kh.totalEx.entity;

import com.kh.totalEx.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity // JPA Entity 클래스임을 지정, Entity 클래스는 반드시 기본키를 가져야 함
@ToString
public class Item {
    @Id
    @Column(name = "item_id") // 해당키가 기본키임을 지정
    @GeneratedValue(strategy = GenerationType.AUTO) // JPA가 자동으로 생성 전략 결정
    private Long id;           // 상품 코드

    @Column(nullable = false,length = 50)
    private String itemNm;    // 상품명
    @Column(nullable = false)
    private int price;         // 가격
    @Column(nullable = false)
    private int stockNumber;   // 제고 수량
    @Lob // 엄청 긴 문자열 생성해야될경우
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
