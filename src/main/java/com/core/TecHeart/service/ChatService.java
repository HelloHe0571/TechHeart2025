package com.core.TecHeart.service;

import com.core.TecHeart.entity.ChatMessage;
import com.core.TecHeart.entity.ChatSession;
import com.core.TecHeart.model.Result;

import java.util.List;

public interface ChatService {
    Result<ChatSession> createSession(ChatSession session);
    Result<List<ChatMessage>> getMessagesBySession(String sessionId);
    Result<ChatMessage> sendMessage(ChatMessage message);
    Result<Void> archiveSession(String sessionId);
    Result<ChatSession> selectByUserId(String userId);
}
