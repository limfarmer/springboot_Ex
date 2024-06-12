package com.kh.totalEx.controller;

import com.kh.totalEx.dto.MemberRequestDto;
import com.kh.totalEx.dto.MemberResponseDto;
import com.kh.totalEx.entity.Member;
import com.kh.totalEx.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 정보 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberResponseDto>> memberList(){
        List<MemberResponseDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }

    // 개인 정보 조회
    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> getMemberInfo(MemberRequestDto memberRequestDto){
        MemberResponseDto rs = memberService.getMemberInfo(memberRequestDto);
        return ResponseEntity.ok(rs);
    }

}
