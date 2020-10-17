package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.PokemonAcademyApi.app_loader.repository.*;
import pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.PokeApiPokemonDetailsRepository;
import pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.PokemonDetailsResponse;
import pl.sdaacademy.PokemonAcademyApi.app_loader.service.PokemonDetailsTransformer;
import pl.sdaacademy.PokemonAcademyApi.common.NoPokemonFoundException;
import pl.sdaacademy.PokemonAcademyApi.common.repository.Pokemon;
import pl.sdaacademy.PokemonAcademyApi.common.repository.PokemonRepository;
import pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.repository.PokemonDetailsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PokemonDetailsService {
    private final PokemonRepository pokemonRepository;
    private final PokeApiPokemonDetailsRepository pokeApiPokemonDetailsRepository;
    private final PokemonDetailsTransformer pokemonDetailsTransformer;
    private final PokemonDetailsRepository pokemonDetailsRepository;

    @Autowired
    public PokemonDetailsService(PokemonRepository pokemonRepository,
                                 PokeApiPokemonDetailsRepository pokeApiPokemonDetailsRepository,
                                 PokemonDetailsRepository pokemonDetailsRepository,
                                 PokemonDetailsTransformer pokemonDetailsTransformer) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiPokemonDetailsRepository = pokeApiPokemonDetailsRepository;
        this.pokemonDetailsTransformer = pokemonDetailsTransformer;
        this.pokemonDetailsRepository = pokemonDetailsRepository;
    }

    public PokemonDetails getPokemonDetails(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name).orElseThrow(() -> {
            throw new NoPokemonFoundException(name);
        });
        return providePokemonDetails(pokemon);
    }

    public List<PokemonDetails> getPokemonDetails(List<String> pokemonNames) {
        return pokemonNames.stream().map(pokemonRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::providePokemonDetails)
                .collect(Collectors.toList());
    }

    private PokemonDetails providePokemonDetails(Pokemon pokemon) {
        return pokemonDetailsRepository
                .findById(pokemon.getName())
                .orElseGet(() -> {
                    PokemonDetails pokemonDetails =
                            getPokemonDetailsFromApi(pokemon.getUrl());
                    savePokemonDetailsToRepo(pokemonDetails);
                    return pokemonDetails;
                });
    }

    private PokemonDetails getPokemonDetailsFromApi(String url) {
        PokemonDetailsResponse response = pokeApiPokemonDetailsRepository
                .pokemonDetailsResponse(url);
        return pokemonDetailsTransformer.transformToPokemonDetails(response);
    }

    private void savePokemonDetailsToRepo(PokemonDetails details) {
        pokemonDetailsRepository.findById(details.getName())
                .orElseGet(() -> pokemonDetailsRepository.save(details));
    }
}