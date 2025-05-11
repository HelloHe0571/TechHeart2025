package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.*;
import com.core.TecHeart.mapper.*;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatSessionMapper sessionMapper;
    @Autowired
    private ChatMessageMapper messageMapper;

    @Override
    public Result<ChatSession> createSession(ChatSession session) {
        Result<ChatSession> result = new Result<>();
        try {
            session.setSessionId(UUID.randomUUID().toString());
            session.setCreatedAt(LocalDateTime.now());
            session.setUpdatedAt(LocalDateTime.now());
            session.setSessionStatus("active");

            int flag = sessionMapper.insert(session);
            if(flag > 0) {
                result.setResultSuccess("会话创建成功", session);
            } else {
                result.setResultFailed("会话创建失败");
            }
        } catch (Exception e) {
            result.setResultFailed("系统异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<ChatMessage>> getMessagesBySession(String sessionId) {
        Result<List<ChatMessage>> result = new Result<>();
        try {
            List<ChatMessage> messages = messageMapper.selectBySessionId(sessionId);
            if(!messages.isEmpty()) {
                result.setResultSuccess("消息查询成功", messages);
            } else {
                result.setResultFailed("未找到相关消息");
            }
        } catch (Exception e) {
            result.setResultFailed("查询异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<ChatMessage> sendMessage(ChatMessage message) {
        Result<ChatMessage> result = new Result<>();
        try {
            // 验证会话是否存在
            ChatSession session = sessionMapper.selectBySessionId(message.getSessionId());
            if(session == null) {
                result.setResultFailed("会话不存在");
                return result;
            }

            message.setCreatedAt(LocalDateTime.now());
            int flag = messageMapper.insert(message);
            if(flag > 0) {
                // 更新会话最后活跃时间
                session.setUpdatedAt(LocalDateTime.now());
                sessionMapper.update(session);

                result.setResultSuccess("消息发送成功", message);
            } else {
                result.setResultFailed("消息发送失败");
            }
        } catch (Exception e) {
            result.setResultFailed("发送异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Void> archiveSession(String sessionId) {
        Result<Void> result = new Result<>();
        try {
            ChatSession session = sessionMapper.selectBySessionId(sessionId);
            if(session != null) {
                session.setSessionStatus("archived");
                session.setUpdatedAt(LocalDateTime.now());
                int flag = sessionMapper.update(session);
                if(flag > 0) {
                    result.setResultSuccess("会话归档成功");
                } else {
                    result.setResultFailed("会话归档失败");
                }
            } else {
                result.setResultFailed("会话不存在");
            }
        } catch (Exception e) {
            result.setResultFailed("归档异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<ChatSession> selectByUserId(String userId) {
        Result<ChatSession> result = new Result<>();
        try{
            List<ChatSession> list = sessionMapper.selectByUserId(userId);
            result.setResultSuccess("会话获取成功",list.get(0));
        }catch (Exception e){
            result.setResultFailed("系统异常: " + e.getMessage());
        }
        return result;
    }

}
