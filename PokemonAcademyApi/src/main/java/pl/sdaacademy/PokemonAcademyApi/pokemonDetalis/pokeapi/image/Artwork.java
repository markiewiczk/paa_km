package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artwork {
    @JsonProperty("front_default")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
