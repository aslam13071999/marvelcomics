package com.example.marvelcomics.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Character Data Transfer Object")
public class CharacterDto {
    @Schema(description = "Character ID", example = "1")
    private Long id;
    
    @Schema(description = "Character name", example = "Spider-Man")
    private String name;
    
    @Schema(description = "Character's power", example = "Web-slinging")
    private String power;
    
    @Schema(description = "Number of kills", example = "0")
    private Long killCount;
}
