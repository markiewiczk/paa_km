package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Type {
    @JsonProperty("type")
    private TypeDetails typeDetails;

    public TypeDetails getTypeDetails() {
        return typeDetails;
    }

    public void setTypeDetails(TypeDetails typeDetails) {
        this.typeDetails = typeDetails;
    }
}
