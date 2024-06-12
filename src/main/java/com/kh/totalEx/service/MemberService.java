package com.kh.totalEx.service;

import com.kh.totalEx.dto.MemberRequestDto;
import com.kh.totalEx.dto.MemberResponseDto;
import com.kh.totalEx.entity.Member;
import com.kh.totalEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    
    // 회원 정보 전체 조회
    public List<MemberResponseDto> getMemberList(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            memberResponseDtos.add(convertEntityToDto(member));
        }
        return memberResponseDtos;
    }
    // 개별 회원 조회
    public MemberResponseDto getMemberInfo(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail()).orElseThrow(
                () -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        System.out.println(member.getEmail() + " email check");
        return convertEntityToDto(member);
    }

    //
    private MemberResponseDto convertEntityToDto(Member member){
        MemberResponseDto memberDto = new MemberResponseDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setName(member.getName());
        memberDto.setImage(member.getImage());
        memberDto.setRegDate(member.getRegDate());
        return memberDto;
    }
}
