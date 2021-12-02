package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private static double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.setNumber(number);
        this.setSize(size);
        this.setPricePerPerson(pricePerPerson);
        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();
    }

    private void setNumber(int number) {
        this.number = number;
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    protected Collection<HealthyFood> getHealthyFood() {
        return healthyFood;
    }

    protected Collection<Beverages> getBeverages() {
        return beverages;
    }


    //    • healthyFood - Collection<HealthyFood> accessible only by the base class
    //    • beverages – Collection<Beverages> accessible only by the base class
    //    • number – int the table number
    //    • size - int the table size
    //        ◦ It can’t be less than zero. In these cases, throw an IllegalArgumentException with message "Size has to be greater than 0!".
    //    • numberOfPeople - int the counter of people who want a table
    //        ◦ It can’t be less than or equal to 0. In these cases, throw an IllegalArgumentException with message "Cannot place zero or less people!".
    //    • pricePerPerson - double the price per person for the table
    //    • isReservedTable - boolean returns true if the table is reserved, otherwise false
    //    • allPeople - double calculates the price for all people


    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        //TODO:
//        for (HealthyFood food : healthyFood) {
//            allPeople += food.getPrice();
//        }
//
//        for (Beverages beverage : beverages) {
//            allPeople += beverage.getPrice();
//        }
//        return allPeople;

        return 0;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double bill = 0;
        for (HealthyFood food : healthyFood) {
            bill += food.getPrice();
        }

        for (Beverages beverage : beverages) {
            bill += beverage.getPrice();
        }

        bill += numberOfPeople * pricePerPerson;
        return bill;
    }

    @Override
    public void clear() {
        healthyFood.clear();
        beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.pricePerPerson = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Table - %d%n", this.getTableNumber()));
        str.append(String.format("Size - %d%n", this.getSize()));
        str.append(String.format("Type - %s%n", this.getClass().getSimpleName()));
        str.append(String.format("All price - %.2f", this.pricePerPerson()));
        return str.toString();
    }
}

//"Table - {table number}"
//"Size - {table size}"
//"Type - {table type}"
//"All price - {price per person for the current table}"
