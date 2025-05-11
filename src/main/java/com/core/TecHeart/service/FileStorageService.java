package com.core.TecHeart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {
    @Value("${knowledge.storage-path}")
    private String storagePath;

    public Path getLibraryPath(String libraryName) {
        return Paths.get(storagePath, libraryName);
    }

    public Path storeFile(MultipartFile file, String libraryName) throws IOException {
        Path targetLocation = getLibraryPath(libraryName)
                .resolve(Objects.requireNonNull(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return targetLocation;
    }

    public void deleteFile(String libraryName, String fileName) throws IOException {
        Path filePath = getLibraryPath(libraryName).resolve(fileName).normalize();
        Files.deleteIfExists(filePath);
    }
}
