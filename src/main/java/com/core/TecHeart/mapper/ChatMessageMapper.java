package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int insert(ChatMessage message);
    List<ChatMessage> selectBySessionId(String sessionId);
    int deleteBySessionId(String sessionId);
}
