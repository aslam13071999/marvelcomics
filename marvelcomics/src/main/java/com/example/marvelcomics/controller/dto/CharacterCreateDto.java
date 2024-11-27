package com.example.marvelcomics.controller.dto;

import lombok.Data;

@Data
public class CharacterCreateDto {
    private String name;
    private String power;
    private Long killCount;
}
