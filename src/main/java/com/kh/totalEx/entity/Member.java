package com.kh.totalEx.entity;

import com.kh.totalEx.constant.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String pw;
    private String userId;
    @Column(unique = true)
    private String email;
    private String image;
    private LocalDateTime regDate;

    //권한(유저, 어드민)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member( String email, String pw, String name,String image,  Authority authority) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.authority = authority;
        this.image = image;
        this.regDate = LocalDateTime.now();
    }

}
