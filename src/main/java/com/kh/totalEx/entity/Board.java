package com.kh.totalEx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long board_id;
    private String title;
    @Lob
    @Column(length = 10000)
    private String content;
    @Lob
    private String imagePath;
    private LocalDateTime regDate;

}
