package com.core.TecHeart.controller;

import com.core.TecHeart.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    // 本地存储路径（修改为你需要的路径）
    private final String UPLOAD_DIR = "D:/uploads/";

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        Result<String> result = new Result<>();

        try {
            // 校验文件类型
            String contentType = file.getContentType();
            if (contentType == null ||
                    (!contentType.startsWith("image/jpeg") &&
                            !contentType.startsWith("image/png"))) {
                result.setResultFailed("只支持JPG/PNG格式");
                return result;
            }

            // 创建日期目录
            String dateDir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            Path dirPath = Paths.get(UPLOAD_DIR, dateDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() + fileExt;

            // 保存文件
            Path filePath = dirPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // 返回相对路径
            String fileUrl = "/uploads/" + dateDir + "/" + fileName;

            result.setResultSuccess("文件上传成功", fileUrl);
            return result;

        } catch (IOException e) {
            result.setResultFailed("文件上传失败: " + e.getMessage());
            return result;
        } catch (Exception e) {
            result.setResultFailed("服务器处理异常: " + e.getMessage());
            return result;
        }
    }
}
