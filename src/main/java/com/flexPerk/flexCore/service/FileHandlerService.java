package com.flexPerk.flexCore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileHandlerService {

    private final Path rootLocation = Paths.get("/Users/saranganjanakan/IdeaProjects/FlexCore/src/main/resources/assets");
    public void uploadImage(long id, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String fileName = id + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public byte[] getProfileImage(long id) {
        try {
            Path filePath = findProfileImage(id);
            if (filePath != null) {
                return Files.readAllBytes(filePath);
            } else {
                throw new RuntimeException("Could not find profile image for id: " + id);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    private Path findProfileImage(long id) throws IOException {
        try (var paths = Files.list(rootLocation)) {
            return paths
                    .filter(path -> path.getFileName().toString().startsWith(id + "_"))
                    .findFirst()
                    .orElse(null);
        }
    }
}
