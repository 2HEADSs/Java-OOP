package climbers.repositories;

import climbers.models.climber.Climber;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClimberRepository implements Repository<Climber> {
    private Map<String, Climber> climbers;

    public ClimberRepository() {
        this.climbers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Climber> getCollection() {
        return Collections.unmodifiableCollection(climbers.values());
    }

    @Override
    public void add(Climber climber) {
        this.climbers.put(climber.getName(), climber);
    }

    @Override
    public boolean remove(Climber climber) {
        if (this.climbers.containsKey(climber.getName())) {
            this.climbers.remove(climber.getName());
            return true;
        }
        return false;
    }

    @Override
    public Climber byName(String name) {
        if (climbers.containsKey(name)) {
            return this.climbers.get(name);
        } else {
            return null;
        }

    }

}
