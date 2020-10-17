package pl.sdaacademy.PokemonAcademyApi.common;

public class NoPokemonFoundException extends RuntimeException {

    public NoPokemonFoundException(String pokemonName) {
        super(String.format("No pokemon %s found", pokemonName));
    }
}
