package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.sdaacademy.PokemonAcademyApi.app_loader.repository.PokemonDetails;
import pl.sdaacademy.PokemonAcademyApi.common.repository.Pokemon;


public interface PokemonDetailsRepository extends CrudRepository<PokemonDetails, String> {

}
