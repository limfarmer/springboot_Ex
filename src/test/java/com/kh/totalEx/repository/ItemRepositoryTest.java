package com.kh.totalEx.repository;

import com.kh.totalEx.constant.ItemSellStatus;
import com.kh.totalEx.entity.Item;
import com.kh.totalEx.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    @Test
    @DisplayName(" 상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");
        for (Item e : itemList){
            System.out.println("테스트 상품 디테일" + e.getItemDetail());
        }
    }
    @Test
    @DisplayName(" 상품명 또는 상품 상세 설명 조회")
    public void findByItemNmOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("으어으아","테스트 상품 상세 설명 1");
        for (Item e : itemList){
            System.out.println(e.getItemNm());
        }
    }
    @Test
    @DisplayName("상품 가격 비교")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item e : itemList){
            System.out.println(e.toString());
        }
    }
    @Test
    @DisplayName("상품 가격 비교후 정렬")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item e : itemList){
            System.out.println(e.toString());
        }
    }
    //
    @Test
    @DisplayName("개별 회원 정보 조회")
    public void createMemInfo(){
        for(int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setEmail("dla@naver.com"+ i);
            member.setPw("1234" + i);
            member.setName("임정후"+ i);
            member.setImage("/image/im.jpg"+ i);
            member.setRegDate(LocalDateTime.now());
            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("개별회원 조회")
    public void findByEmailTest(){
        this.createMemInfo();
        Optional<Member> memberList = memberRepository.findByEmail("dla@naver.com1");
        memberList.ifPresent(member -> System.out.println(member.getName()));
    }
    @Test
    @DisplayName("전체 회원 조회")
    public void findAllByEmailTest(){
        this.createMemInfo();
        List<Member> memberList = memberRepository.findAll();
        for (Member e : memberList){
        System.out.println(e.getEmail());
        }
    }
    @Test
    @DisplayName("가입여부 확인")
    public void findByEmailTestCheckReg(){
        this.createMemInfo();
        Optional<Member> memberList = memberRepository.findByEmail("dla@naver.com1");
        System.out.println(memberList.isPresent());
    }
    @Test
    @DisplayName("로그인 체크")
    public void findByEmailAndPwTest(){
        this.createMemInfo();
        List<Member> memberList = memberRepository.findByEmailAndPw("dla@naver.com10","123410");
        System.out.println(memberList.toString());
    }

    @Test
    @DisplayName("JPQl을 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for (Item e : itemList){
            System.out.println(e.toString());
        }
    }
    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailBy(){
        this.createItemTest();
        List<Item> itemList = itemRepository.nativeQuery("테스트 상품 상세 설명");
        for (Item e : itemList){
            System.out.println(e.toString());
        }
    }
}