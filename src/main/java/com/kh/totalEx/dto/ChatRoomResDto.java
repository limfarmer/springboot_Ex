package com.kh.totalEx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Slf4j
public class ChatRoomResDto {
    private String roomId;
    private String name;
    private LocalDateTime regDate;
    
    @JsonIgnore //
    private Set<WebSocketSession> sessions; // 채팅방에 입장한 세션 전부를 담을 Set(집합 개념)

    public boolean isSessionEmpty(){
        return this.sessions.isEmpty();
    }

    @Builder
    public ChatRoomResDto(String roomId, String name, LocalDateTime regDate) {
        this.roomId = roomId;
        this.name = name;
        this.regDate = regDate;
        // 동시에 접근하는 문제를 해결하기 위해 추가 했다고 함
        this.sessions = Collections.newSetFromMap(new ConcurrentHashMap<>()); // 동시성 문제를 해결하기 위해 ConcurrentHashMap 사용
    }
}
