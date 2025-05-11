package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.ChatSession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatSessionMapper {
    int insert(ChatSession session);
    int update(ChatSession session);
    int delete(String sessionId);
    ChatSession selectBySessionId(String sessionId);
    List<ChatSession> selectByUserId(String userId);
}
