package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokeApiPokemonDetailsRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public PokeApiPokemonDetailsRepository(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public PokemonDetailsResponse pokemonDetailsResponse(String url) {
        return restTemplate.getForObject(url, PokemonDetailsResponse.class);
    }
}
