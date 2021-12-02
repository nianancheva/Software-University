package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public class BaseTable implements Table {
    protected Collection<HealthyFood> healthyFood;
    protected Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable (int number, int size, double pricePerPerson) {
        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();
        this.size = size;
        this.pricePerPerson = pricePerPerson;
    }

    public void reserve (int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    public double bill () {
       return 0;
    }

    public void clear() {
        healthyFood.clear();
        beverages.clear();
        setReservedTable(false);
        setNumberOfPeople(0);
    }

    public String tableInformation() {
        StringBuilder str = new StringBuilder();

        str.append(String.format("Table - %d%n", number));
        str.append(String.format("Size - %d%n", size));
        str.append(String.format("Type - %s%n", this.getClass().getSimpleName()));
        str.append(String.format("All price - %d", allPeople));
        return str.toString();
    }


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

    protected void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    protected void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }


}
