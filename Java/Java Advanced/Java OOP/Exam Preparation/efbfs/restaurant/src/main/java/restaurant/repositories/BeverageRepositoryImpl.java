package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.Collection;

public abstract class BeverageRepositoryImpl<T extends Beverages> implements BeverageRepository<T> {
    @Override
    public T beverageByName(String drinkName, String drinkBrand) {
        return null;
    }

    @Override
    public Collection<T> getAllEntities() {
        return null;
    }

    @Override
    public void add(T entity) {

    }
}
