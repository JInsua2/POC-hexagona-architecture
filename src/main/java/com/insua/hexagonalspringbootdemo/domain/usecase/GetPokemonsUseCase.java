package com.insua.hexagonalspringbootdemo.domain.usecase;

import com.insua.hexagonalspringbootdemo.domain.entity.Pokemon;
import java.util.List;

public interface GetPokemonsUseCase {

    List<Pokemon> getPokemons();

}
