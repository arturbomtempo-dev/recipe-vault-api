package dev.arturbomtempo.recipe_vault_api.service;

import dev.arturbomtempo.recipe_vault_api.dto.request.RecipeRequest;
import dev.arturbomtempo.recipe_vault_api.dto.response.RecipeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    RecipeResponse create(RecipeRequest request, MultipartFile image);

    List<RecipeResponse> findAll(String title);

    RecipeResponse findById(UUID id);

    RecipeResponse update(UUID id, RecipeRequest request);

    void delete(UUID id);
}
