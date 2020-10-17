package pl.sdaacademy.PokemonAcademyApi.app_loader.repository;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class PokemonDetails {

    private String name;
    private int height;
    private int weight;
    @ElementCollection
    private List<String> ability;
    @ElementCollection
    private List<String> type;
    @Id
    private String image;


    public PokemonDetails() {

    }

    public PokemonDetails(String name, int height, int weight, List<String> ability, List<String> type, String image) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.ability = ability;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getAbility() {
        return ability;
    }

    public void setAbility(List<String> ability) {
        this.ability = ability;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
