package dev.arturbomtempo.recipe_vault_api.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String upload(MultipartFile file);
}
