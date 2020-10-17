package pl.sdaacademy.PokemonAcademyApi.app_loader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sdaacademy.PokemonAcademyApi.app_loader.repository.*;
import pl.sdaacademy.PokemonAcademyApi.common.repository.Pokemon;
import pl.sdaacademy.PokemonAcademyApi.common.repository.PokemonRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class PokemonLoaderService {
    private PokeApiRepository pokeApiRepository;
    private PokemonRepository pokemonRepository;
    private PokemonTransformer pokemonTransformer;
    private int startOffset;
    private int limit;

    @Autowired
    public PokemonLoaderService(PokeApiRepository pokeApiRepository,
                                PokemonRepository pokemonRepository,
                                PokemonTransformer pokemonTransformer,
                                @Value("${pokeapi.start_offset}") int startOffset,
                                @Value("${pokeapi.limit}") int limit) {
        this.pokeApiRepository = pokeApiRepository;
        this.pokemonRepository = pokemonRepository;
        this.pokemonTransformer = pokemonTransformer;
        this.startOffset = startOffset;
        this.limit = limit;

    }


    @PostConstruct
    public void loadPokemonList() {
        PokemonResponse pokemonResponse;
        List<PokemonResult> pokemonResults = new ArrayList();
        int offset = startOffset;
        int limit = this.limit;
        do {
            pokemonResponse = pokeApiRepository.getPokemonsResponse(limit, offset);
            pokemonResults.addAll(pokemonResponse.getResults());
            offset += limit;
        } while (pokemonResponse.getNext() != null);


        List<Pokemon> pokemons = pokemonTransformer.transformToPokemonList(pokemonResults);
        pokemonRepository.saveAll(pokemons);
    }
}
