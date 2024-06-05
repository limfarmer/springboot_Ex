package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 이밑이랑 같은 의미 select itemNum from item where itemNum = ?
    // find + 엔티티이름 + By 엔티티명(오타내면 안됨)
    List<Item> findByItemNm(String itemNm);
    // findBy문에서 entity이름이랑 다를경우 오류가 어디서 나는지 모르고 그냥 죽어버림
    //Item findByItemNamAndPrice(String itemNm, int price);
    // OR 조건 처리 하기
    List<Item> findByPrice(int price);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // LessThan : ~보다 작다
    List<Item> findByPriceLessThan(int price);

    // 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(int priec);

    // JPQL 쿼리 : from에 들어가는 테이블 명은 entity클래스 명으로 -> 객체 지향 언어라서 db로 접근안하고 entity 테이블로 매핑하기 때문
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc ")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // nativeQuery -> nativeQuery는 함수 명에 제약 없음
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> nativeQuery(@Param("itemDetail") String itemDetail);



}
