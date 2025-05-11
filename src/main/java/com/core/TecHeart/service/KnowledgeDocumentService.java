package com.core.TecHeart.service;

import com.core.TecHeart.entity.KnowledgeDocument;
import com.core.TecHeart.model.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KnowledgeDocumentService {
    Result<?> uploadDocument(Integer libraryId, MultipartFile file);
    Result<List<KnowledgeDocument>> getDocuments(Integer libraryId);
//    Result<?> deleteDocument(Integer docId);
}