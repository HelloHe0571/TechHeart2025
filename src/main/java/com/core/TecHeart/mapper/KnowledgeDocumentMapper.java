package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.KnowledgeDocument;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeDocumentMapper {
    int insert(KnowledgeDocument document);
    int delete(Integer id);
    List<KnowledgeDocument> selectByLibraryId(Integer libraryId);
    Long countByLibraryId(Integer libraryId);
}