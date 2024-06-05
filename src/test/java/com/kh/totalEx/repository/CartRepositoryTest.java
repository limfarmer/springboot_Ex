package com.kh.totalEx.repository;



import com.kh.totalEx.entity.Cart;
import com.kh.totalEx.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest  // 스프링 컨텍스트를 로드하여 테스트 환경 설정
@Transactional   // 데이터베이스의 논리적인 작업 단위, 모두 성공하는 경우가 아니면 롤백
@Slf4j           // 로딩 데이터 처리를 위해 사용
@TestPropertySource(locations="classpath:application-test.properties") // 테스트에 사용할 프로퍼티 파일의 위치를 지정
class CartTest {
    @Autowired   // 스프링 컨테이너에서 해당 빈에 해당하는 의존성을 주입 받음
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext  // JPA의 EntityManager를 주입
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("JKS2024");
        member.setPw("SPHB8250");
        member.setName("곰돌이사육사");
        member.setEmail("jks2024@gmail.com");
        member.setRegDate(LocalDateTime.now());
        return member;
    }

    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        Member member = createMemberInfo();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // 연속성 컨텍스트에 데이터 저장 후  flush() 호출하여 데이터베이스에 반영
        em.clear(); // 영속성 켄텍스트를 비움

        // cart.getId()를 기반으로 데이터베이스에서 해당 ID에 해당하는 장바구니 엔티티를 조회
        // 조회된 장바구니 엔티티가 존재하는 않을 경우, 예외를 발생 시킴
        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        //log.warn(saveCart.toString());
        // 조회된 장바구니 엔티티의 회원 ID와 기존에 생성한 회원 엔티티를 비교하여 올바른지 확인
        assertEquals(saveCart.getMember().getUserId(), member.getUserId());
    }
}