package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import javax.lang.model.element.Name;
import java.util.LinkedHashMap;
import java.util.Map;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<String, HealthyFood> healthyFood;
    private Map<String, Beverages> beverages;
    private Map<Integer, Table> tables;

    private static double totalMoney;
    private static double money;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        healthyFood = new LinkedHashMap<>();
        beverages = new LinkedHashMap<>();
        tables = new LinkedHashMap<>();
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        //TODO:
        HealthyFood food = null;

        if (type.equals("Salad")) {
            food = new Salad(name, price);
        } else if (type.equals("VeganBiscuits")) {
            food = new VeganBiscuits(name, price);
        }

        if (healthyFood.containsKey(name)) {
            throw new IllegalArgumentException((String.format(FOOD_EXIST, name)));
        }

        healthyFood.put(name, food);
        return (String.format(FOOD_ADDED, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        //TODO:

        Beverages beverage = null;

        if (type.equals("Smoothie")) {
            beverage = new Smoothie(name, counter, brand);
        } else if (type.equals("Fresh")) {
            beverage = new Fresh(name, counter, brand);
        }

        if (beverages.containsKey(name) && beverages.get(name).getBrand().equals(brand)) {
            throw new IllegalArgumentException((String.format(BEVERAGE_EXIST, name)));
        }

        beverages.put(name, beverage);
        return (String.format(BEVERAGE_ADDED, type, brand));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        //TODO:
        Table table = null;
        if (type.equals("Indoors")) {
            table = new Indoors(tableNumber, capacity);
        } else if (type.equals("InGarden")) {
            table = new InGarden(tableNumber, capacity);
        }

        if (tables.containsKey(tableNumber)) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }

        tables.put(tableNumber, table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        //TODO:

        for (Table table : tables.values()) {
            if (table.getSize() >= numberOfPeople && !table.isReservedTable()) {
                table.reserve(numberOfPeople);
                totalMoney += table.bill();
                return String.format(TABLE_RESERVED, table.getTableNumber(), table.numberOfPeople());
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        //TODO:
        boolean tableExists = false;
        for (Table table : tables.values()) {
            if (table.getTableNumber() == tableNumber) {
                tableExists = true;
            }
        }

        if (!tableExists) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        if (!healthyFood.containsKey(healthyFoodName)) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        totalMoney += healthyFood.get(healthyFoodName).getPrice();
        tables.get(tableNumber).orderHealthy(healthyFood.get(healthyFoodName));
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        //TODO:
        boolean tableExists = false;
        for (Table table : tables.values()) {
            if (table.getTableNumber() == tableNumber) {
                tableExists = true;
            }
        }

        if (!tableExists) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        if (!beverages.containsKey(name)) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        totalMoney += beverages.get(name).getPrice();
        tables.get(tableNumber).orderBeverages(beverages.get(name));
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        //TODO:
        double bill = 0;
        for (Map.Entry<Integer, Table> integerTableEntry : tables.entrySet()) {
            if (integerTableEntry.getKey() == tableNumber) {
                bill = integerTableEntry.getValue().bill();
                money += bill;
                integerTableEntry.getValue().clear();
                break;
            }
        }

        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        //TODO:
        return String.format(TOTAL_MONEY, money);
    }
}
