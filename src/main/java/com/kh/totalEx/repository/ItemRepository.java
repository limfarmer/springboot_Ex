package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
