package com.insua.hexagonalspringbootdemo.api;

import com.insua.hexagonalspringbootdemo.domain.entity.Pokemon;
import com.insua.hexagonalspringbootdemo.domain.usecase.GetPokemonsUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private GetPokemonsUseCase getPokemonsUseCase;

    @RequestMapping(value = "getPokemons")
    public List<Pokemon> pokemons() {
        return getPokemonsUseCase.getPokemons();
    }

}
