package dev.arturbomtempo.recipe_vault_api.service.impl;

import dev.arturbomtempo.recipe_vault_api.dto.request.RecipeRequest;
import dev.arturbomtempo.recipe_vault_api.dto.response.RecipeResponse;
import dev.arturbomtempo.recipe_vault_api.entity.Recipe;
import dev.arturbomtempo.recipe_vault_api.exception.RecipeNotFoundException;
import dev.arturbomtempo.recipe_vault_api.infrastructure.storage.ImageStorageService;
import dev.arturbomtempo.recipe_vault_api.mapper.RecipeMapper;
import dev.arturbomtempo.recipe_vault_api.repository.RecipeRepository;
import dev.arturbomtempo.recipe_vault_api.service.RecipeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;
    private final ImageStorageService storageService;
    private final RecipeMapper mapper;

    @Override
    public RecipeResponse create(@NonNull RecipeRequest request, MultipartFile image) {
        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            imageUrl = storageService.upload(image);
        }

        Recipe recipe = Recipe.builder()
                .title(request.title())
                .ingredients(request.ingredients())
                .instructions(request.instructions())
                .prepTimeMinutes(request.prepTimeMinutes())
                .servings(request.servings())
                .imageUrl(imageUrl)
                .build();

        @SuppressWarnings("null")
        Recipe savedRecipe = repository.save(recipe);
        return mapper.toResponse(savedRecipe);
    }

    @Override
    public List<RecipeResponse> findAll(String title) {
        List<Recipe> recipes = (title == null)
                ? repository.findAll()
                : repository.findByTitleContainingIgnoreCase(title);

        return recipes.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public RecipeResponse findById(@NonNull UUID id) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

        return mapper.toResponse(recipe);
    }

    @Override
    public RecipeResponse update(@NonNull UUID id, @NonNull RecipeRequest request, MultipartFile image) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

        recipe.setTitle(request.title());
        recipe.setIngredients(request.ingredients());
        recipe.setInstructions(request.instructions());
        recipe.setPrepTimeMinutes(request.prepTimeMinutes());
        recipe.setServings(request.servings());

        if (image != null && !image.isEmpty()) {
            String imageUrl = storageService.upload(image);
            recipe.setImageUrl(imageUrl);
        }

        return mapper.toResponse(repository.save(recipe));
    }

    @Override
    @SuppressWarnings("null")
    public void delete(@NonNull UUID id) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

        repository.delete(recipe);
    }
}
