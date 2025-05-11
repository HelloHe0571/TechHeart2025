package com.core.TecHeart.controller;

import com.core.TecHeart.entity.KnowledgeLibrary;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.KnowledgeLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeLibraryController {

    @Autowired
    private KnowledgeLibraryService libraryService;

    @PostMapping("/create")
    public Result<KnowledgeLibrary> create(@RequestBody KnowledgeLibrary library) {
        return libraryService.create(library);
    }

    @PostMapping("/update")
    public Result<KnowledgeLibrary> update(@RequestBody KnowledgeLibrary library) {
        return libraryService.update(library);
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        return libraryService.delete(id);
    }

    @GetMapping("/get/{id}")
    public Result<KnowledgeLibrary> getById(@PathVariable Integer id) {
        return libraryService.getById(id);
    }

    @GetMapping("/all")
    public Result<List<KnowledgeLibrary>> getAll() {
        return libraryService.getAll();
    }

    @PostMapping("/update-doc-count/{id}")
    public Result<Void> updateDocCount(
            @PathVariable Integer id,
            @RequestParam int increment
    ) {
        return libraryService.updateDocumentCount(id, increment);
    }

    @PostMapping("/toggle-status")
    public Result<Void> toggleStatus(@RequestBody KnowledgeLibrary library) {
        return libraryService.toggleStatus(library);
    }
}