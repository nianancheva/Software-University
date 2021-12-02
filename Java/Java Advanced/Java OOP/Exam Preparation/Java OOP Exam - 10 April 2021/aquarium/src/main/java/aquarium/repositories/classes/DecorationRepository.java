package aquarium.repositories.classes;

import aquarium.entities.decorations.Decoration;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DecorationRepository implements Repository {
    private final Collection<Decoration> decorations;

    public DecorationRepository() {
        decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
        decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        for (Decoration decoration1 : decorations) {
            if (decoration1 == decoration) {
                decorations.remove(decoration);
                return true;
            }
        }
        return false;
    }

    @Override
    public Decoration findByType(String type) {
        for (Decoration decoration : decorations) {
            if (decoration.getClass().getSimpleName().equals(type)) {
                return decoration;
            }
        }
        return null;
    }

    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(decorations);
    }
}
