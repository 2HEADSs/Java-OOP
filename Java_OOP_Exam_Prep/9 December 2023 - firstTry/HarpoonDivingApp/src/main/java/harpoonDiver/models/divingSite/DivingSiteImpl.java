package harpoonDiver.models.divingSite;

import harpoonDiver.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DivingSiteImpl implements DivingSite{
    private String name;
    private List<String> seaCreatures;

    public DivingSiteImpl(String name) {
        this.setName(name);
        this.seaCreatures = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getSeaCreatures() {
        return this.seaCreatures;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
