package com.kh.totalEx.dto;

import com.kh.totalEx.entity.Member;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String name;
    private String email;
    private String image;
    private LocalDateTime regDate;

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .image(member.getImage())
                .regDate(member.getRegDate())
                .build();
    }
}
