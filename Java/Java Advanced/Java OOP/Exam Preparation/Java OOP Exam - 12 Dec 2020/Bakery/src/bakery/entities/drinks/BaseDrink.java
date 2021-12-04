package bakery.entities.drinks;

import bakery.entities.drinks.interfaces.Drink;

import static bakery.common.ExceptionMessages.*;

public class BaseDrink implements Drink {
    private String name;
    private int portion;
    private double price;
    private String brand;

    protected BaseDrink(String name, int portion, double price, String brand) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
        this.setBrand(brand);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public void setPortion(int portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_BRAND);
        }
        this.brand = brand;
    }

    //    • name - String
    //        ◦ If the name is null or whitespace,
    //        throw an IllegalArgumentException with message "Name cannot be null or white space!"
    //    • portion - int
    //        ◦ If the portion is less or equal to 0,
    //        throw an IllegalArgumentException with message "Portion cannot be less or equal to zero!"
    //    • price - double
    //        ◦ If the portion is less or equal to 0,
    //        throw an IllegalArgumentException with message "Price cannot be less or equal to zero!"
    //    • brand - String
    //        ◦ If the name is null or whitespace,
    //        throw an IllegalArgumentException with message "Brand cannot be null or white space!"

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s %s - %dml - %.2flv",
                this.getName(), this.getBrand(), this.getPortion(), this.getPrice()));

        return str.toString();
    }
}
