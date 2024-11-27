package com.example.marvelcomics.repository;

import com.example.marvelcomics.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByName(String name);
    List<Character> findByPower(String power);
    List<Character> findByKillCountGreaterThan(long killCount);
}
