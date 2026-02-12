package dev.arturbomtempo.recipe_vault_api.dto.response;

import java.util.UUID;

public record RecipeResponse(
        UUID id,
        String title,
        String ingredients,
        String instructions,
        int prepTimeMinutes,
        int servings,
        String imageUrl
) {
}
