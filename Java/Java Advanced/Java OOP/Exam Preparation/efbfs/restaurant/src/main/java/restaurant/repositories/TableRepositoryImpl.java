package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.Collection;

public abstract class TableRepositoryImpl<T extends Table> implements TableRepository<T> {
    @Override
    public Collection getAllEntities() {
        return null;
    }

    @Override
    public void add(T entity) {

    }

    @Override
    public T byNumber(int number) {
        return null;
    }
}
