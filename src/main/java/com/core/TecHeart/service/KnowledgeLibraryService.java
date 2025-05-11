package com.core.TecHeart.service;

import com.core.TecHeart.entity.KnowledgeLibrary;
import com.core.TecHeart.model.Result;
import java.util.List;

public interface KnowledgeLibraryService {
    Result<KnowledgeLibrary> create(KnowledgeLibrary library);
    Result<KnowledgeLibrary> update(KnowledgeLibrary library);
    Result<Void> delete(Integer id);
    Result<KnowledgeLibrary> getById(Integer id);
    Result<List<KnowledgeLibrary>> getAll();
    Result<Void> updateDocumentCount(Integer id, int increment);
    Result<Void> toggleStatus(KnowledgeLibrary library);
}