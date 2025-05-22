package com.example.amazonclone.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config-file}")
    private Resource configFile;

    @Value("${firebase.storage-bucket}")
    private String storageBucket;

    @PostConstruct
    public void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(configFile.getInputStream()))
                    .setStorageBucket(storageBucket)
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("✅ Firebase инициализирован");
        }
    }
}
