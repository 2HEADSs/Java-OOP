package org.rpg_lab;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HeroTest {
    private static final int EXPERIENCE = 200;
    @Test
    public void test_Hero_Gets_Experience_When_Target_Dies() {
        Weapon weapon = Mockito.mock(Weapon.class);
        Target target =  Mockito.mock(Target.class);
        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(EXPERIENCE);

        Hero hero = new Hero("Caligula", weapon);
        hero.attack(target);
        assertEquals(EXPERIENCE, hero.getExperience());
    }

}