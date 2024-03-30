package handball.repositories;

import handball.entities.equipment.Equipment;

import java.util.ArrayList;
import java.util.Collection;

public class EquipmentRepository implements Repository {
    private Collection<Equipment> equipments;

    public EquipmentRepository() {
        equipments = new ArrayList<>();
    }

    @Override
    public void add(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        if (equipments.contains(equipment)) {
            equipments.remove(equipment);
            return true;
        }
        return false;
    }

    @Override
    public Equipment findByType(String type) {
        return equipments.stream().filter(e -> e.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
