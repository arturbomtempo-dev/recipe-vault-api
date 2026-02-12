package dev.arturbomtempo.recipe_vault_api.repository;

import dev.arturbomtempo.recipe_vault_api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    List<Recipe> findByTitleContainingIgnoreCase(String title);
}
