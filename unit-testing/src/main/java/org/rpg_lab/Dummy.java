package org.rpg_lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Dummy implements Target {

    private int health;
    private int experience;
    private List<Weapon> weapons;

    public Dummy(int health, int experience, List<Weapon> weapons) {
        this.health = health;
        this.experience = experience;
        this.weapons = weapons;
    }

    public Dummy(int health, int experience) {
        this(health, experience, new ArrayList<>());
    }

    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    @Override
    public Optional<Weapon> getLoot() {
        if(weapons.isEmpty()){
            return Optional.empty();
        }
        int index = ThreadLocalRandom.current().nextInt(0, weapons.size());
        return Optional.of(weapons.remove(index));
    }

    @Override
    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    @Override
    public boolean isDead() {
        return this.health <= 0;
    }
}
