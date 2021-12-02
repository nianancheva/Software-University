package main.java.aquarium.entities.decorations;

public abstract class BaseDecoration implements aquarium.entities.decorations.Decoration {
    private int comfort;
    private double price;

    protected BaseDecoration(int comfort, double price) {
        this.setComfort(comfort);
        this.setPrice(price);
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getComfort() {
        return comfort;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
