package com.core.TecHeart.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private Long messageId;
    private String sessionId;
    private String content;
    private Boolean isAi;
    private LocalDateTime createdAt;
    private String messageType; // text/image/file

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAi() {
        return isAi;
    }

    public void setAi(Boolean ai) {
        isAi = ai;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
