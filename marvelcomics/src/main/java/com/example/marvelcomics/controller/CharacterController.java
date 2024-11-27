package com.example.marvelcomics.controller;

import com.example.marvelcomics.controller.dto.CharacterCreateDto;
import com.example.marvelcomics.controller.dto.CharacterDto;
import com.example.marvelcomics.controller.dto.CharacterSimpleDto;
import com.example.marvelcomics.entity.Character;
import com.example.marvelcomics.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor
@Tag(name = "Character Controller", description = "APIs for managing Marvel characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Create a new character")
    @PostMapping("/")
    public CharacterDto addCharacter(@Valid @RequestBody CharacterCreateDto request) {
        com.example.marvelcomics.entity.Character character = new com.example.marvelcomics.entity.Character();
        character.setName(request.getName());
        character.setPower(request.getPower());
        character.setKillCount(request.getKillCount());
        
        com.example.marvelcomics.entity.Character savedCharacter = characterService.saveCharacter(character);
        return convertToDto(savedCharacter);
    }

    @Operation(summary = "Get all characters")
    @GetMapping("/")
    public List<CharacterDto> getAllCharacters() {
        return characterService.getAllCharacters().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Delete a character by ID")
    @ApiResponse(responseCode = "200", description = "Character successfully deleted")
    @ApiResponse(responseCode = "404", description = "Character not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        characterService.deleteCharacter(id);
        return ResponseEntity.ok().build();
    }

    // Get simplified list of characters (ID and Name only)
    @Operation(summary = "Get simplified list of characters (ID and Name only)")
    @GetMapping("/simple")
    public List<CharacterSimpleDto> getSimpleCharacterList() {
        return characterService.getAllCharacters().stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    // Get character details by ID
    @Operation(summary = "Get character details by ID")
    @ApiResponse(responseCode = "200", description = "Character found")
    @ApiResponse(responseCode = "404", description = "Character not found")
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(character));
    }

    private CharacterDto convertToDto(com.example.marvelcomics.entity.Character character) {
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setPower(character.getPower());
        dto.setKillCount(character.getKillCount());
        return dto;
    }

    private CharacterSimpleDto convertToSimpleDto(Character character) {
        CharacterSimpleDto dto = new CharacterSimpleDto();
        dto.setId(character.getId());
        dto.setName(character.getName());
        return dto;
    }
}
