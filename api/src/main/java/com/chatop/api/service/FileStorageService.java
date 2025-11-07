package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;


    @Value("${app.upload.url:/uploads/}")
    private String uploadUrl;

    // Base URL for serving files, e.g. http://localhost:3001
    @Value("${app.base.url:http://localhost:3001}")
    private String baseUrl;

    /**
     * Save the file to disk and return its public URL.
     */
    public String storeFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            // Always use absolute path for uploads directory (relative to project root)
            File uploadBaseDir = new File(uploadDir);
            if (!uploadBaseDir.isAbsolute()) {
                uploadBaseDir = new File(System.getProperty("user.dir"), uploadDir);
            }
            if (!uploadBaseDir.exists()) uploadBaseDir.mkdirs();
            File dest = new File(uploadBaseDir, fileName);
            file.transferTo(dest);
            // Return only the filename
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    /**
     * Get the File object for a given filename in the uploads directory.
     */
    public File getFile(String filename) {
        File uploadBaseDir = new File(uploadDir);
        if (!uploadBaseDir.isAbsolute()) {
            uploadBaseDir = new File(System.getProperty("user.dir"), uploadDir);
        }
        return new File(uploadBaseDir, filename);
    }
}