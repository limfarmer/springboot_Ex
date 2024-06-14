package com.kh.totalEx.controller;

import com.kh.totalEx.dto.ChatRoomReqDto;
import com.kh.totalEx.dto.ChatRoomResDto;
import com.kh.totalEx.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    @PostMapping("/new")
    public ResponseEntity<String> createRoom(@RequestBody ChatRoomReqDto chatRoomDto) {
        log.warn("chatRoomDto : {}", chatRoomDto);
        ChatRoomResDto room = chatService.createRoom(chatRoomDto.getName());
        System.out.println(room.getRoomId());
        return new ResponseEntity<>(room.getRoomId(), HttpStatus.OK);
    }
    @GetMapping("/list")
    public List<ChatRoomResDto> findAllRoom() {
        return chatService.findAllRoom();
    }

    // 방 정보 가져오기
    @GetMapping("/room/{roomId}")
    public ChatRoomResDto findRoomById(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }
}
