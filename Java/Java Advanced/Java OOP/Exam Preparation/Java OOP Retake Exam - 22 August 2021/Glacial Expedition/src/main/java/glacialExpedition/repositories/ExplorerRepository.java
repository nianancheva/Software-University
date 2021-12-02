package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.Collection;

public class ExplorerRepository implements Repository<Explorer>{
    private Collection<Explorer> explorers;

    @Override
    public Collection<Explorer> getCollection() {
        return null;
    }

    @Override
    public void add(Explorer entity) {

    }

    @Override
    public boolean remove(Explorer entity) {
        return false;
    }

    @Override
    public Explorer byName(String name) {
        return null;
    }
}
