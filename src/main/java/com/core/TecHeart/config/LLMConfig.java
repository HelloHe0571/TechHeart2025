package com.core.TecHeart.config;


import com.core.TecHeart.entity.KnowledgeLibrary;
import com.core.TecHeart.mapper.KnowledgeLibraryMapper;
import com.core.TecHeart.service.ChatAssistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.aggregator.DefaultContentAggregator;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Autowired
    private KnowledgeLibraryMapper knowledgeLibraryMapper;

    @Bean
    public QdrantClient qdrantClient() {
        QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder("127.0.0.1", 6334, false);
        return new QdrantClient(grpcClientBuilder.build());
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return QdrantEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6334)
                .collectionName(knowledgeLibraryMapper.getActiveLibrary())
                .build();
    }

    @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel(){
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-turbo")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-turbo")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(ChatLanguageModel chatLanguageModel, StreamingChatLanguageModel streamingChatLanguageModel, EmbeddingStore<TextSegment> embeddingStore) {

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        DefaultRetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryTransformer(new CompressingQueryTransformer(chatLanguageModel))  // 查询增强
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore)) // 内容源 单个直接配置
                .contentAggregator(new DefaultContentAggregator()) // 匹配结果聚合
                .contentInjector(new DefaultContentInjector())   // 结果提示词注入
                .build();

        return AiServices.builder(ChatAssistant.class)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemory(chatMemory)
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }


}
