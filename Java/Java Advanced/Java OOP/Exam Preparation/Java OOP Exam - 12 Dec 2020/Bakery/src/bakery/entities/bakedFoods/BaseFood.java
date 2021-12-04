package bakery.entities.bakedFoods;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseFood implements BakedFood {
    private String name;
    private double portion;
    private double price;

    protected BaseFood(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    private void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    //    • name - String
    //        ◦ If the name is null or whitespace,
    //        throw an IllegalArgumentException with message "Name cannot be null or white space!"
    //    • portion - double
    //        ◦ If the portion is less or equal to 0,
    //        throw an IllegalArgumentException with message "Portion cannot be less or equal to zero!"
    //    • price - double
    //        ◦ If the portion is less or equal to 0,
    //        throw an IllegalArgumentException with message "Price cannot be less or equal to zero!"

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s: %.2fg - %.2f", this.getName(), this.getPortion(), this.getPrice()));

        return str.toString();
    }
}
