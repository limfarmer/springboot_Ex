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
    boolean existByEmail(String email);
}
