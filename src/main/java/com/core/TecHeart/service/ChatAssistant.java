package com.core.TecHeart.service;

import reactor.core.publisher.Flux;

public interface ChatAssistant {
    Flux<String> chat(String userMessage);
}
