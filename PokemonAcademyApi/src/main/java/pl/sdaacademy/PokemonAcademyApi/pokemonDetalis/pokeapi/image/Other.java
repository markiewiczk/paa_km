package pl.sdaacademy.PokemonAcademyApi.pokemonDetalis.pokeapi.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Other {
    @JsonProperty("official-artwork")
    private Artwork officialArtwork;

    public Artwork getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(Artwork officialArtwork) {
        this.officialArtwork = officialArtwork;
    }
}
