package pl.sdaacademy.PokemonAcademyApi.app_loader.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokeApiRepository {
    private final String urlAddress;
    private final RestTemplate restTemplate;

    @Autowired
    public PokeApiRepository(RestTemplate restTemplate, @Value("${pokeapi.url}") String urlAddress) {
        this.restTemplate = restTemplate;
        this.urlAddress = urlAddress;
    }


    public PokemonResponse getPokemonsResponse(int limit, int offset) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                String.format(urlAddress, limit, offset), PokemonResponse.class);
    }
}
