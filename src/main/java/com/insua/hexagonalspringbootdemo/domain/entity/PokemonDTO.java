package com.insua.hexagonalspringbootdemo.domain.entity;

import lombok.Builder;

@Builder
public record PokemonDTO(
    String name,
    String type,
    String hp) {

}