package aquarium.entities.decorations.classes;

import aquarium.entities.decorations.Decoration;

public abstract class BaseDecoration implements Decoration {
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
