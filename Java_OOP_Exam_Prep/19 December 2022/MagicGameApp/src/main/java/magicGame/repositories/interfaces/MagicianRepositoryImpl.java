package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.Magician;
import magicGame.models.magics.Magic;
import magicGame.repositories.interfaces.MagicianRepository;

import java.util.ArrayList;
import java.util.Collection;

public class MagicianRepositoryImpl implements MagicianRepository {
    private Collection<Magician> data;

    public MagicianRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection getData() {
        return this.data;
    }

    @Override
    public void addMagician(Magician magician) {
        if (magician == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_REPOSITORY);
        }
        this.data.add(magician);
    }

    @Override
    public boolean removeMagician(Magician magician) {
        if (data.contains(magician)) {
            data.remove(magician);
            return true;
        }
        return false;
    }

    @Override
    public Object findByUsername(String name) {
        for (Magician magician : this.data) {
            if (magician.getUsername().equals(name)) {
                return magician;
            }
        }
        return null;
    }
}
