package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {
    Collection<BakedFood> models;

    public FoodRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        for (BakedFood model : models) {
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(BakedFood bakedFood) {
        models.add(bakedFood);
    }
}
