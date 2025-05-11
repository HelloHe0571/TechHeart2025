package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.KnowledgeLibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface KnowledgeLibraryMapper {
    int insert(KnowledgeLibrary library);
    int update(KnowledgeLibrary library);
    int delete(Integer id);
    KnowledgeLibrary selectById(Integer id);
    List<KnowledgeLibrary> selectAll();
    List<KnowledgeLibrary> selectByUserId(Integer userId);
    int updateDocCount(Map<String, Object> params);
    int toggleStatus(Map<String, Object> params);
    int disableOthers(@Param("currentId") Integer currentId);
    String getActiveLibrary();
}
