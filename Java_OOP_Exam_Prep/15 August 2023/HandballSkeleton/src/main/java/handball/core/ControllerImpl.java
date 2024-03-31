package handball.core;

import handball.common.ConstantMessages;
import handball.common.ExceptionMessages;
import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private Repository equipmentRepo;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipmentRepo = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay;
        if (gameplayType.equals("Outdoor")) {
            gameplay = new Outdoor(gameplayName);
        } else if (gameplayType.equals("Indoor")) {
            gameplay = new Indoor(gameplayName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_GAMEPLAY_TYPE);
        }
        gameplays.add(gameplay);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipment;
        if (equipmentType.equals("Kneepad")) {
            equipment = new Kneepad();
        } else if (equipmentType.equals("ElbowPad")) {
            equipment = new ElbowPad();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_EQUIPMENT_TYPE);
        }
        equipmentRepo.add(equipment);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment equipment = equipmentRepo.findByType(equipmentType);
        if (equipment == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_EQUIPMENT_FOUND, equipmentType));
        }

        Gameplay gameplay = this.gameplays
                .stream()
                .filter(e -> e.getName().equals(gameplayName))
                .findFirst().get();

        gameplay.addEquipment(equipment);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team;
        if (teamType.equals("Bulgaria")) {
            team = new Bulgaria(teamName, country, advantage);
        } else if (teamType.equals("Germany")) {
            team = new Germany(teamName, country, advantage);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TEAM_TYPE);
        }
//        Indoor
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                if (teamType.equals("Bulgaria")) {
                    if (gameplay.getClass().getSimpleName().equals("Outdoor")) {
                        gameplay.addTeam(team);
                        return String.format(
                                ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName
                        );
                    } else {
                        return String.format(ConstantMessages.GAMEPLAY_NOT_SUITABLE);
                    }
                }
                if (gameplay.getClass().getSimpleName().equals("Indoor")) {
                    gameplay.addTeam(team);
                    return String.format(
                            ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName
                    );
                } else {
                    return String.format(ConstantMessages.GAMEPLAY_NOT_SUITABLE);
                }
            }
        }
        return null;
    }

    @Override
    public String playInGameplay(String gameplayName) {
        int count = 0;
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                for (Team team : gameplay.getTeam()) {
                    team.play();
                    count++;
                }
            }
        }
        return String.format(ConstantMessages.TEAMS_PLAYED, count);
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        int percantage = 0;
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                for (Team team : gameplay.getTeam()) {
                    percantage += team.getAdvantage();
                }
            }
        }
        return String.format(ConstantMessages.ADVANTAGE_GAMEPLAY, gameplayName, percantage);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Gameplay gameplay : gameplays) {
            sb.append(gameplay.getName() + " " + gameplay.getClass().getSimpleName());
            sb.append(System.lineSeparator());
            sb.append(gameplay.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
