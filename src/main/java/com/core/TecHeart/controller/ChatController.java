package com.core.TecHeart.controller;

import com.core.TecHeart.entity.ChatMessage;
import com.core.TecHeart.entity.ChatSession;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired private ChatService chatService;

    @PostMapping("/session/create")
    public Result<ChatSession> createSession(@RequestBody ChatSession session) {
        return chatService.createSession(session);
    }

    @GetMapping("/messages/{sessionId}")
    public Result<List<ChatMessage>> getMessages(@PathVariable String sessionId) {
        return chatService.getMessagesBySession(sessionId);
    }

    @PostMapping("/message/send")
    public Result<ChatMessage> sendMessage(@RequestBody ChatMessage message) {
        return chatService.sendMessage(message);
    }

    @PutMapping("/session/archive/{sessionId}")
    public Result<Void> archiveSession(@PathVariable String sessionId) {
        return chatService.archiveSession(sessionId);
    }

    @GetMapping("/session/{userId}")
    public Result<ChatSession> selectByUserId(@PathVariable String userId) {
        return chatService.selectByUserId(userId);
    }
}