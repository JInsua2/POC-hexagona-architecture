package com.insua.hexagonalspringbootdemo.domain.repository;

import com.insua.hexagonalspringbootdemo.domain.entity.Pokemon;
import java.util.List;

public interface PokemonRepository {

    public List<String> getPokemons();

}
