package com.core.TecHeart.service.impl;

import com.core.TecHeart.controller.AIController;
import com.core.TecHeart.entity.KnowledgeDocument;
import com.core.TecHeart.entity.KnowledgeLibrary;
import com.core.TecHeart.mapper.KnowledgeDocumentMapper;
import com.core.TecHeart.mapper.KnowledgeLibraryMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.FileStorageService;
import com.core.TecHeart.service.KnowledgeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeDocumentServiceImpl implements KnowledgeDocumentService {
    @Autowired
    private KnowledgeDocumentMapper documentMapper;
    @Autowired
    private KnowledgeLibraryMapper libraryMapper;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private AIController aiController;

    @Override
    public Result<?> uploadDocument(Integer libraryId, MultipartFile file) {
        Result<?> result = new Result<>();
        try {
            KnowledgeLibrary library = libraryMapper.selectById(libraryId);
            Path filePath = storageService.storeFile(file, library.getName());

            KnowledgeDocument doc = new KnowledgeDocument();
            doc.setLibraryId(libraryId);
            doc.setFileName(file.getOriginalFilename());
            doc.setFileType(getFileType(file.getOriginalFilename()));
            doc.setFileSize(file.getSize());
            documentMapper.insert(doc);

            libraryMapper.updateDocCount(Map.of("id", libraryId, "increment", 1));
            aiController.putDocument(filePath.toString());
            result.setResultSuccess("文件上传成功");
        } catch (Exception e) {
            result.setResultFailed("文件上传失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<KnowledgeDocument>> getDocuments(Integer libraryId) {
        Result<List<KnowledgeDocument>> result = new Result<>();
        try{
            List<KnowledgeDocument> list = documentMapper.selectByLibraryId(libraryId);
            result.setResultSuccess("查询成功",list);
        }catch (Exception e){
            result.setResultFailed("查询失败");
        }
        return result;
    }

//    @Override
//    public Result<?> deleteDocument(Integer docId) {
//        try {
//            KnowledgeDocument doc = documentMapper.selectById(docId);
//            KnowledgeLibrary library = libraryMapper.selectById(doc.getLibraryId());
//            storageService.deleteFile(library.getName(), doc.getFileName());
//            documentMapper.delete(docId);
//            libraryMapper.updateDocCount(Map.of("id", doc.getLibraryId(), "increment", -1));
//            return Result.success("文件删除成功");
//        } catch (Exception e) {
//            return Result.failed("文件删除失败: " + e.getMessage());
//        }
//    }

    private String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
