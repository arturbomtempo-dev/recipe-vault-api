package dev.arturbomtempo.recipe_vault_api.infrastructure.storage;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryStorageService implements ImageStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) {

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = (Map<String, Object>) cloudinary.uploader()
                    .upload(file.getBytes(), Map.of(
                            "folder", "recipe_vault"));

            Object secureUrl = result.get("secure_url");

            if (secureUrl == null) {
                throw new RuntimeException("Cloudinary did not return secure_url");
            }

            return secureUrl.toString();
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
}
