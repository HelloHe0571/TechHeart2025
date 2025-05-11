package com.core.TecHeart.controller;

import com.core.TecHeart.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.github.pigmesh.ai.deepseek.core.DeepSeekClient;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class AIController {

    @Autowired
    private ChatAssistant chatAssistant;

    @Autowired
    private QdrantClient qdrantClient;

    @Autowired
    private DeepSeekClient deepSeekClient;

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @RequestMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(@RequestParam String message) {
        return chatAssistant.chat(message)
                .delayElements(Duration.ofMillis(50))
                // 关键修改：标准化SSE格式
                .map(data -> {
                    // 清理数据中的特殊字符
                    String cleanedData = data
                            .replaceAll("^data:\\s*", "")  // 清除开头可能存在的data:
                            .replace("\n", " ")  // 将换行转换为空格（根据需求调整）
                            .replace("data:", "") // 防止内容中自带data:前缀
                            .trim();

                    // 标准化SSE格式（注意结尾双换行）
                    return "data: " + cleanedData + "\n\n";
                })
                // 添加SSE结束标识（可选）
                .concatWithValues("data: [DONE]");
    }

    @RequestMapping("/put")
    public void putDocument(String filePath){
        Document document = FileSystemDocumentLoader.loadDocument(filePath);
        EmbeddingStoreIngestor.ingest(document,embeddingStore);
    }

    @RequestMapping(value = "/chatDeepSeek", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatCompletionResponse> chatDeepSeek(@RequestParam String prompt) {
        return deepSeekClient.chatFluxCompletion(prompt);
    }



}
