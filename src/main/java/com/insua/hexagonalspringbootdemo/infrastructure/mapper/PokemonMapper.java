package com.insua.hexagonalspringbootdemo.infrastructure.mapper;


import com.insua.hexagonalspringbootdemo.domain.entity.Pokemon;
import com.insua.hexagonalspringbootdemo.domain.entity.PokemonDTO;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public static PokemonDTO asPokemonDTO(Pokemon pokemon) {
        return PokemonDTO.builder()
            .name(pokemon.name())
            .type(pokemon.type())
            .hp(pokemon.hp())
            .build();
    }

    public static Pokemon asPokemon(PokemonDTO dto) {
        return Pokemon.builder()
            .id(UUID.randomUUID()) // Generar un nuevo ID
            .name(dto.name())
            .type(dto.type())
            .hp(dto.hp())
            .build();
    }

}
