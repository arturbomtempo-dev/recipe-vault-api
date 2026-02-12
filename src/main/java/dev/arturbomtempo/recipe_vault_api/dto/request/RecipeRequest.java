package dev.arturbomtempo.recipe_vault_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecipeRequest(

        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 120)
        String title,

        @NotBlank(message = "Ingredients are required")
        @Size(min = 10, max = 3000)
        String ingredients,

        @NotBlank(message = "Instructions are required")
        @Size(min = 20, max = 8000)
        String instructions,

        @Min(value = 1, message = "Preparation time must be at least 1 minute")
        int prepTimeMinutes,

        @Min(value = 1, message = "Servings must be at least 1")
        int servings
) {
}
