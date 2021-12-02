package restaurant.repositories;

import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.Collection;

public abstract class HealthFoodRepositoryImpl<T extends HealthyFood> implements HealthFoodRepository<T> {
    @Override
    public T foodByName(String name) {
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
