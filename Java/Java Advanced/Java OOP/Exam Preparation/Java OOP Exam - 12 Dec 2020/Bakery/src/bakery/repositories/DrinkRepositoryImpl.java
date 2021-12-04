package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    Collection<Drink> models;

    public DrinkRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        for (Drink model : models) {
            if (model.getName().equals(drinkName) && model.getBrand().equals(drinkBrand)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Collection<Drink> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Drink drink) {
        models.add(drink);
    }
}
