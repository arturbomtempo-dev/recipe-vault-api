package dev.arturbomtempo.recipe_vault_api.exception;

import java.util.UUID;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(UUID id) {
        super("Recipe with ID " + id + " was not found");
    }
}
