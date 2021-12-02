package aquarium.entities.fish.classes;

public class SaltwaterFish extends BaseFish{
    private int size = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void eat() {
        this.size = size + 3;
        super.setSize(size);
    }
}
