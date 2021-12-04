package bakery.core;

import bakery.common.ExceptionMessages.*;
import bakery.common.OutputMessages.*;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.List;

import static bakery.common.ExceptionMessages.*;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
    }


    @Override
    public String addFood(String type, String name, double price) {
        //TODO:
        BakedFood byName = this.foodRepository.getByName(name);
        if (byName != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        BakedFood bakedFood = null;
        if (type.equals("Bread")) {
            bakedFood = new Bread(name, price);
        } else if (type.equals("Cake")) {
            bakedFood = new Cake(name, price);
        }
        foodRepository.add(bakedFood);
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        if (drinkRepository.getByNameAndBrand(name, brand) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        //TODO:
        Drink drink = null;
        if (type.equals("Water")) {
            drink = new Water(name, portion, brand);
        } else if (type.equals("Tea")) {
            drink = new Tea(name, portion, brand);
        }
        drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        if (tableRepository.getByNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }
        //TODO:
        Table table = null;
        if (type.equals("InsideTable")) {
            table = new InsideTable(tableNumber, capacity);
        } else if (type.equals("OutsideTable")) {
            table = new OutsideTable(tableNumber, capacity);
        }
        tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        //TODO:
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved() && table.getCapacity() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        //TODO:
        if (foodRepository.getByName(foodName) == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        BakedFood food = foodRepository.getByName(foodName);
        for (Table table : tableRepository.getAll()) {
            if (table.getTableNumber() == tableNumber && table.isReserved()) {
                table.orderFood(food);
                return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
            }
        }
        return String.format(WRONG_TABLE_NUMBER, tableNumber);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        //TODO:
        if (drinkRepository.getByNameAndBrand(drinkName, drinkBrand) == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        for (Table table : tableRepository.getAll()) {
            if (table.getTableNumber() == tableNumber && table.isReserved()) {
                table.orderDrink(drink);
                return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
            }
        }
        return String.format(WRONG_TABLE_NUMBER, tableNumber);
    }

    @Override
    public String leaveTable(int tableNumber) {
        //TODO:
        double bill = 0;
        for (Table table : tableRepository.getAll()) {
            if (table.getTableNumber() == tableNumber) {
                bill = table.getBill();
                totalIncome += bill;
                table.clear();
                break;
            }
        }
        StringBuilder str = new StringBuilder();
        str.append(String.format("Table: %d%n", tableNumber));
        str.append(String.format("Bill: %.2f", bill));
        return str.toString();
    }

    @Override
    public String getFreeTablesInfo() {
        //TODO:
        StringBuilder str = new StringBuilder();
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved()) {
                str.append(table.getFreeTableInfo());
                //str.append(System.lineSeparator());
            }
        }
        return str.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        //TODO:
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
