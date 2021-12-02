package aquarium.entities.fish.classes;

public class FreshwaterFish extends BaseFish{
    private int size = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void eat() {
        this.size = size + 2;
        super.setSize(size);
    }
}
