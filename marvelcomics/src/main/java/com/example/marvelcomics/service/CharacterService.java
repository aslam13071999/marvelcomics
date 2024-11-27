package com.example.marvelcomics.service;


import com.example.marvelcomics.repository.CharacterRepository;
import com.example.marvelcomics.entity.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }
    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}
