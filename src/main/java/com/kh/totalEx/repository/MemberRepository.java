package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {
    // Null을 방지
    Optional<Member> findByEmail(String email); // 개별 회원 정보 조회
    List<Member> findByEmailAndPw(String email, String pw);
    //List<Member> findBy
    // 과제
    // Junit 테스트에서
    // 1. 전체 회원 조회
    // 2. 이메일로 개별 회원 조회
    // 3. 가입여부 확인(이메일로 확인)
    // 4. 로그인 체크(email, pw)
}
