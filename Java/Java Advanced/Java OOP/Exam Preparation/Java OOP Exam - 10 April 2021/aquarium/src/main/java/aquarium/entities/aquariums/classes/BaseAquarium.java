package aquarium.entities.aquariums.classes;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        decorations = new ArrayList<>();
        fish = new ArrayList<>();
    }

    //    • name - String
    //        ◦ If the name is null or whitespace, throw an NullPointerException with message:
    //"Aquarium name cannot be null or empty."
    //        ◦ All names are unique
    //    • capacity -  int
    //        ◦ The number of Fish аn Aquarium can have
    //    • decorations - Collection<Decoration>
    //    • fish - Collection<Fish>

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //---------

    @Override
    public int calculateComfort() {
        int comfort = 0;
        for (Decoration decoration : decorations) {
            comfort += decoration.getComfort();
        }
        return comfort;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFish(Fish fish) {
        if (capacity <= this.fish.size()) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        for (Fish fish1 : this.fish) {
            if (fish1 == fish) {
                this.fish.remove(fish1);
                return;
            }
        }
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish1 : fish) {
            fish1.eat();
        }
    }

    @Override
    public String getInfo() {

        StringBuilder str = new StringBuilder();
        str.append(String.format("%s (%s):%n", this.getName(), this.getClass().getSimpleName()));
        str.append("Fish: ");

        StringBuilder fishes = new StringBuilder();
        if (fish.isEmpty()) {
            str.append(String.format("none"));
        } else {
            for (Fish fish1 : fish) {
                fishes.append(String.format("%s ", fish1.getName()));
            }
        }
        String trim = fishes.toString().trim();
        str.append(trim);
        str.append(System.lineSeparator());
        str.append(String.format("Decorations: %d%n", decorations.size()));
        str.append(String.format("Comfort: %d", this.getAquariumComfort()));

        return str.toString().trim();
    }

    private int getAquariumComfort() {
        int comfort = 0;
        for (Decoration decoration : decorations) {
            comfort += decoration.getComfort();
        }
        return comfort;
    }

    @Override
    public double totalFishPrice() {
        double price = 0;
        for (Fish fish1 : fish) {
            price += fish1.getPrice();
        }
        return price;
    }

    @Override
    public double totalDecorationsPrice() {
        double price = 0;
        for (Decoration decoration : decorations) {
            price += decoration.getPrice();
        }

        return price;
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }
}
