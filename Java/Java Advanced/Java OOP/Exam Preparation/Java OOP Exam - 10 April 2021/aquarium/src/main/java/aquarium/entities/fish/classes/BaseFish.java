package aquarium.entities.fish.classes;

import aquarium.entities.fish.Fish;

import static aquarium.common.ExceptionMessages.*;

public abstract class BaseFish implements Fish {
    private String name;
    private String species;
    private int size;
    private double price;

    protected BaseFish(String name, String species, double price) {
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);
    }

    //    • name - String
    //        ◦ If the name is null or whitespace, throw an NullPointerException with message:
    //"Fish name cannot be null or empty."
    //        ◦ All names are unique
    //    • species -  String
    //        ◦ If the species is null or whitespace, throw an NullPointerException with message:
    //"Fish species cannot be null or empty."
    //    • size -  int
    //        ◦ The size of the Fish
    //    • price - double
    //        ◦ The price of the Fish
    //        ◦ If the price is below or equal 0, throw an IllegalArgumentException with message:
    // "Fish price cannot be below or equal to 0."

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new NullPointerException(SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }


    //---------

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void eat() {
        this.setSize(size + 5);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
