package pl.sdaacademy.PokemonAcademyApi.app_loader.service;

import org.springframework.stereotype.Component;
import pl.sdaacademy.PokemonAcademyApi.app_loader.repository.PokemonDetails;
import pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.PokemonDetailsResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonDetailsTransformer {

    public PokemonDetails transformToPokemonDetails(PokemonDetailsResponse pokemonDetailsResponse) {
        String name = pokemonDetailsResponse.getName();
        int weight = pokemonDetailsResponse.getWeight();
        int height = pokemonDetailsResponse.getHeight();
        String imageUrl = pokemonDetailsResponse
                .getSprites()
                .getOther()
                .getOfficialArtwork()
                .getImageUrl();


        List<String> abilities = pokemonDetailsResponse.getAbilities()
                .stream().map(ability -> ability.getAbilityDetails().getName())
                .collect(Collectors.toList());


        List<String> types = pokemonDetailsResponse.getTypes()
                .stream().map(type -> type.getTypeDetails().getName())
                .collect(Collectors.toList());

        return new PokemonDetails(name, height, weight, abilities, types, imageUrl);


    }
}
