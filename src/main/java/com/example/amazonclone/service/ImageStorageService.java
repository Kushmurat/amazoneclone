package com.example.amazonclone.service;

import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageStorageService {

    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try {
            BlobInfo blobInfo = StorageClient.getInstance().bucket().create(fileName, file.getBytes(), file.getContentType());
            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                    blobInfo.getBucket(), blobInfo.getName().replace("/", "%2F"));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл", e);
        }
    }
}
