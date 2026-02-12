package dev.arturbomtempo.recipe_vault_api.controller;

import dev.arturbomtempo.recipe_vault_api.dto.request.RecipeRequest;
import dev.arturbomtempo.recipe_vault_api.dto.response.RecipeResponse;
import dev.arturbomtempo.recipe_vault_api.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecipeResponse> create(
            @Valid @ModelAttribute RecipeRequest request,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request, image));
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> findAll(
            @RequestParam(required = false) String title) {
        return ResponseEntity.ok(service.findAll(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody RecipeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
