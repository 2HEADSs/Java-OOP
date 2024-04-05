package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;
import magicGame.repositories.interfaces.MagicRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MagicRepositoryImpl implements MagicRepository {

    private Collection<Magic> data;

    public MagicRepositoryImpl() {
        this.data = new ArrayList<>();
    }


    @Override
    public Collection getData() {
        return this.data;
    }

    @Override
    public void addMagic(Magic magic) {
        if(magic == null){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_REPOSITORY);
        }
        this.data.add(magic);
    }

    @Override
    public boolean removeMagic(Magic magic) {
        if (data.contains(magic)) {
            data.remove(magic);
            return true;
        }
        return false;

    }

    @Override
    public Object findByName(String name) {
        for (Magic magic : this.data) {
                if(magic.getName().equals(name)){
                    return magic;
                }
        }
        return null;
    }
}
