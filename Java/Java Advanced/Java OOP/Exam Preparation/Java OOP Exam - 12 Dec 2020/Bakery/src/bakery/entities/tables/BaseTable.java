package bakery.entities.tables;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.setTableNumber(tableNumber);
        this.setCapacity(capacity);
        this.setPricePerPerson(pricePerPerson);
        foodOrders = new ArrayList<>();
        drinkOrders = new ArrayList<>();
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    //    • foodOrders - Collection<BakedFood> accessible only by the base class
    //    • drinkOrders – Collection<Drink> accessible only by the base class
    //    • tableNumber – int the table number
    //    • capacity - int the table capacity.
    //        ◦  It can’t be less than zero. In these cases, throw an IllegalArgumentException with message "Capacity has to be greater than 0"
    //    • numberOfPeople - int the count of people who want a table.
    //        ◦  cannot be less or equal to 0. In these cases, throw an IllegalArgumentException with message "Cannot place zero or less people!"
    //    • pricePerPerson – double the price per person for the table
    //    • isReserved - boolean returns true if the table is reserved, otherwise false.
    //    • price – double calculates the price for all people

    @Override
    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
//        this.price = this.getBill() + (this.getPricePerPerson() * this.getNumberOfPeople());
        return price;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double bill = 0;
        for (BakedFood foodOrder : foodOrders) {
            bill += foodOrder.getPrice();
        }
        for (Drink drinkOrder : drinkOrders) {
            bill += drinkOrder.getPrice();
        }

        bill += this.getPricePerPerson() * this.getNumberOfPeople();

        this.price += bill;

        return bill;
    }

    @Override
    public void clear() {
        foodOrders.clear();
        drinkOrders.clear();
        this.isReserved = false;
        this.numberOfPeople = 0;
        this.price = 0;
    }

    @Override
    public String getFreeTableInfo() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Table: %d%n", this.tableNumber));
        str.append(String.format("Type: %s%n", this.getClass().getSimpleName()));
        str.append(String.format("Capacity: %d%n", this.capacity));
        str.append(String.format("Price per Person: %.2f%n", this.pricePerPerson));

        return str.toString();
    }

    //"Table: {table number}"
    //"Type: {table type}"
    //"Capacity: {table capacity}"
    //"Price per Person: {price per person for the current table}"
}
