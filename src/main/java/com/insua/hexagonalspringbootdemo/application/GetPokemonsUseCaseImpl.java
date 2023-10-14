package com.insua.hexagonalspringbootdemo.application;

import com.insua.hexagonalspringbootdemo.domain.repository.PokemonRepository;
import com.insua.hexagonalspringbootdemo.domain.usecase.GetPokemonsUseCase;
import com.insua.hexagonalspringbootdemo.domain.entity.Pokemon;
import com.insua.hexagonalspringbootdemo.infrastructure.PokemonRepositoryImpl;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPokemonsUseCaseImpl implements GetPokemonsUseCase {

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getPokemons() {

        List<String> pokemonList = pokemonRepository.getPokemons();
        return  pokemonList.stream()
            .map(name -> Pokemon.builder()
                .id(UUID.randomUUID())
                .name(name)
                .type("DefaultType")
                .hp("1")
                .build())
            .collect(Collectors.toList());

    }
}
