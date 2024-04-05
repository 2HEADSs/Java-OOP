package magicGame.models.region;

import com.sun.source.tree.NewArrayTree;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = new ArrayList<>();
        List<Magician> blackWidows = new ArrayList<>();
        for (Magician magician : magicians) {
            if(magician.getClass().getSimpleName().equals("BlackWidow") && magician.isAlive()){
                blackWidows.add(magician);
            }else {
                wizards.add(magician);
            }
        }

        while (!wizards.isEmpty() && !blackWidows.isEmpty()){
            for (Magician wizard : wizards) {
                for (int i = 0; i < blackWidows.size(); i++) {
                    Magician currentWidow = blackWidows.get(i);
                    currentWidow.takeDamage(wizard.getMagic().fire());
                    if(!currentWidow.isAlive()){
                        blackWidows.remove(i);
                        i--;
                    }
                }
            }

            for (Magician widow  : blackWidows) {
                for (int i = 0; i < wizards.size(); i++) {
                    Magician currentWizard = blackWidows.get(i);
                    currentWizard.takeDamage(widow.getMagic().fire());
                    if(!currentWizard.isAlive()){
                        wizards.remove(i);
                        i--;
                    }
                }
            }
        }
        if(wizards.isEmpty()){
            return String.format("Black widows win!");
        }
        return String.format("Wizards win!");
    }
}
