package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 정보 생성")
    public void createBoardContent(){
        Board board = new Board();
        board.setContent("나는 개똥벌레 친구가 없네");
        board.setTitle("성진이의 일기");
        board.setImagePath("./image");
        board.setRegDate(LocalDateTime.now());
        boardRepository.save(board);
    }

    @Test
    @DisplayName("본문 내용에 포함된 키워드로 검색")
    public void findByTitleContaining() {
        this.createBoardContent();
        Optional<Board> boardList = boardRepository.findByContentContaining("벌레");
        boardList.ifPresent(System.out::println);

    }
    @Test
    @DisplayName("게시글 전체 조회")
    public void findAllBoardTest(){
        this.createBoardContent();
        List<Board> boardList = boardRepository.findAll();
        for (Board e : boardList){
        System.out.println(e.toString());
        }
    }
}