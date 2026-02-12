package dev.arturbomtempo.recipe_vault_api.mapper;

import dev.arturbomtempo.recipe_vault_api.dto.response.RecipeResponse;
import dev.arturbomtempo.recipe_vault_api.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeResponse toResponse(Recipe recipe);
}
