package com.core.TecHeart.service.impl;

import com.core.TecHeart.controller.AIController;
import com.core.TecHeart.entity.KnowledgeLibrary;
import com.core.TecHeart.mapper.KnowledgeLibraryMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.KnowledgeLibraryService;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeLibraryServiceImpl implements KnowledgeLibraryService {

    @Value("${knowledge.storage-path}")
    private String storagePath; // 注入配置路径

    @Autowired
    private KnowledgeLibraryMapper libraryMapper;

    @Autowired
    private QdrantClient qdrantClient;

    @Override
    public Result<KnowledgeLibrary> create(KnowledgeLibrary library) {
        Result<KnowledgeLibrary> result = new Result<>();
        try {
            library.setDocCount(0);
            library.setActive(false);
            int flag = libraryMapper.insert(library);

            if(flag > 0) {
                // 数据库保存成功后再创建文件夹
                createStorageDirectory(library.getName());
                createCollection(library.getName());
                result.setResultSuccess("知识库创建成功", library);
            } else {
                result.setResultFailed("知识库创建失败");
            }
        } catch (Exception e) {
            result.setResultFailed("知识库创建失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<KnowledgeLibrary> update(KnowledgeLibrary library) {
        Result<KnowledgeLibrary> result = new Result<>();
        int flag = libraryMapper.update(library);
        if(flag > 0) {
            result.setResultSuccess("知识库更新成功", library);
        } else {
            result.setResultFailed("知识库更新失败");
        }
        return result;
    }

    @Override
    public Result<Void> delete(Integer id) {
        Result<Void> result = new Result<>();
        int flag = libraryMapper.delete(id);
        if(flag > 0) {
            result.setResultSuccess("知识库删除成功");
        } else {
            result.setResultFailed("知识库删除失败");
        }
        return result;
    }

    @Override
    public Result<KnowledgeLibrary> getById(Integer id) {
        Result<KnowledgeLibrary> result = new Result<>();
        KnowledgeLibrary library = libraryMapper.selectById(id);
        if(library != null) {
            result.setResultSuccess("查询成功", library);
        } else {
            result.setResultFailed("知识库不存在");
        }
        return result;
    }

    @Override
    public Result<List<KnowledgeLibrary>> getAll() {
        Result<List<KnowledgeLibrary>> result = new Result<>();
        List<KnowledgeLibrary> list = libraryMapper.selectAll();
        result.setResultSuccess("查询成功", list);
        return result;
    }

    @Override
    public Result<Void> updateDocumentCount(Integer id, int increment) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("increment", increment);

        int flag = libraryMapper.updateDocCount(params);
        Result<Void> result = new Result<>();
        if(flag > 0) {
            result.setResultSuccess("文档计数更新成功");
        } else {
            result.setResultFailed("文档计数更新失败");
        }
        return result;
    }

    @Override
    public Result<Void> toggleStatus(KnowledgeLibrary library) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", library.getId());
        params.put("status", library.getActive());

        int flag = libraryMapper.toggleStatus(params);
        if (library.getActive()){
            libraryMapper.disableOthers(library.getId());
        }
        Result<Void> result = new Result<>();
        if(flag > 0) {
            String msg = library.getActive() ? "知识库已启用" : "知识库已停用";
            result.setResultSuccess(msg);
        } else {
            result.setResultFailed("状态更新失败");
        }
        return result;
    }

    /**
     * 创建知识库存储目录
     * @param libraryName 知识库名称
     */
    private void createStorageDirectory(String libraryName) throws Exception {
        // 清理非法字符（保留字母、数字、中文、下划线和空格）
        String cleanName = libraryName.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5_ ]", "");

        Path path = Paths.get(storagePath, cleanName);

        // 自动创建多级目录
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        } else {
            throw new RuntimeException("目录已存在"); // 根据需求选择是否抛出异常
        }
    }

    public void createCollection(String libraryName){
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(384)
                .build();
        qdrantClient.createCollectionAsync(libraryName, vectorParams);
    }
}