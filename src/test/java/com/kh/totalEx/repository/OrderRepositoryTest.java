package com.kh.totalEx.repository;

import com.kh.totalEx.constant.ItemSellStatus;
import com.kh.totalEx.entity.Item;
import com.kh.totalEx.entity.Member;
import com.kh.totalEx.entity.Order;
import com.kh.totalEx.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemNm("테스트 상품" );
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 정보" );
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100 );
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order();
        for (int i = 0; i < 3; i ++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }
        orderRepository.saveAndFlush(order);
        em.clear();

        // 주문 조회
        Order saveOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        log.info(String.valueOf(saveOrder.getOrderItemList().size()));
    }

    public Order createOrder(){
        Order order = new Order();
        for (int i = 0; i < 3; i ++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }
        Member member = new Member();
        member.setName("ㅇㅣㅁㅈㅓㅇㅎㅜ");
        member.setEmail("dlaeocjf94@naver.com");
        member.setRegDate(LocalDateTime.now());
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("고아 객체 제거 테스트")
    public void orphanRemoveTest(){
        Order order = this.createOrder();
        log.info(String.valueOf(order.getOrderItemList().size()));
        order.getOrderItemList().remove(0);
        em.flush();
    }
}