package com.core.TecHeart.controller;

import com.core.TecHeart.entity.KnowledgeDocument;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.KnowledgeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class KnowledgeDocumentController {
    @Autowired
    private KnowledgeDocumentService documentService;

    @PostMapping("/upload/{libraryId}")
    public Result<?> uploadDocument(
            @PathVariable Integer libraryId,
            @RequestParam("file") MultipartFile file
    ) {
        return documentService.uploadDocument(libraryId, file);
    }

    @GetMapping("/{libraryId}")
    public Result<List<KnowledgeDocument>> getDocuments(@PathVariable Integer libraryId) {
        return documentService.getDocuments(libraryId);
    }

//    @DeleteMapping("/{docId}")
//    public Result<?> deleteDocument(@PathVariable Integer docId) {
//        return documentService.deleteDocument(docId);
//    }
}
