package robotService.repositories;

import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepository implements Repository{

    private Collection<Supplement> supplements;

    public SupplementRepository() {
        this.supplements = new ArrayList<>();
    }

    @Override
    public void addSupplement(Supplement supplement) {
    this.supplements.add(supplement);
    }

    @Override
    public boolean removeSupplement(Supplement supplement) {
        if(this.supplements.contains(supplement)){
            this.supplements.remove(supplement);
            return true;
        }
        return false;
    }

    @Override
    public Supplement findFirst(String type) {
        return this.supplements.stream().filter(s -> s.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
