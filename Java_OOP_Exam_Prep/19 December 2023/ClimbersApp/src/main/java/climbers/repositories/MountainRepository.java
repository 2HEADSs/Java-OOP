package climbers.repositories;

import climbers.models.mountain.Mountain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class MountainRepository implements Repository<Mountain> {
    private Map<String, Mountain> mountains;

    @Override
    public Collection<Mountain> getCollection() {
        return Collections.unmodifiableCollection(mountains.values());
    }

    @Override
    public void add(Mountain mountain) {
        this.mountains.putIfAbsent(mountain.getName(), mountain);
    }

    @Override
    public boolean remove(Mountain mountain) {
        if (this.mountains.containsKey(mountain.getName())) {
            this.mountains.remove(mountain.getName());
            return true;
        }
        return false;
    }

    @Override
    public Mountain byName(String name) {
        if (mountains.containsKey(name)) {
            return this.mountains.get(name);
        } else {
            return null;
        }
    }
}
