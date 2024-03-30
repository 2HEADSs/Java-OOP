package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;
import harpoonDiver.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Diver> diverRepository;
    private Repository<DivingSite> divingSiteRepository;

    public ControllerImpl() {
        diverRepository = new DiverRepository();
        divingSiteRepository = new DivingSiteRepository();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        if (kind.equals("OpenWaterDiver")) {
            diver = new OpenWaterDiver(diverName);
        } else if (kind.equals("WreckDiver")) {
            diver = new WreckDiver(diverName);
        } else if (kind.equals("DeepWaterDiver")) {
            diver = new DeepWaterDiver(diverName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }
        diverRepository.add(diver);
        return String.format(ConstantMessages.DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        for (String seaCreature : seaCreatures) {
            divingSite.getSeaCreatures().add(seaCreature);
        }
        this.divingSiteRepository.add(divingSite);
        return String.format(ConstantMessages.DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(String.format(
                    ExceptionMessages.DIVER_DOES_NOT_EXIST, diverName
            ));
        }
        this.diverRepository.remove(diver);
        return String.format(ConstantMessages.DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        Collection<Diver> diverWithOxygen = this.diverRepository.getCollection()
                .stream()
                .filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());

        if (diverWithOxygen.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }
        DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        diving.searching(divingSite, diverWithOxygen);
        int removedDivers = 0;
        for (Diver diver : diverWithOxygen) {
            if (diver.getOxygen() <= 0) {
                removedDivers++;
            }
        }
        return String.format(ConstantMessages.SITE_DIVING, siteName, removedDivers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        //The dive took place at 3 site/s.
        sb.append(String.format(ConstantMessages.FINAL_DIVING_SITES, divingSiteRepository.getCollection().size()));
        sb.append(System.lineSeparator());
        //Diver's statistics:
        sb.append(ConstantMessages.FINAL_DIVERS_STATISTICS);
        sb.append(System.lineSeparator());

        List<Diver> divers = new ArrayList<>(this.diverRepository.getCollection());

        for (Diver diver : divers) {
            //Name: Peter
            sb.append(String.format(ConstantMessages.FINAL_DIVER_NAME, diver.getName()));
            sb.append(System.lineSeparator());
            //Oxygen: 30
            sb.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, diver.getOxygen()));
            sb.append(System.lineSeparator());
            //Diver's catch: None/ fish
            if (diver.getSeaCatch().getSeaCreatures().isEmpty()) {
                sb.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, "None"));
            } else {
                sb.append(String.format(
                        ConstantMessages.FINAL_DIVER_CATCH, String.join(ConstantMessages.FINAL_DIVER_CATCH_DELIMITER,
                                diver.getSeaCatch().getSeaCreatures())));
            }
                sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
