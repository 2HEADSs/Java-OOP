package org.rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int ATTACK = 10;
    private static final int DURABILITY = 42;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 200;

    private Axe axe;
    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
        this.deadDummy = new Dummy(0, EXPERIENCE);
    }

    @Test
    public void test_Dummy_LoosesHealth_WhenAlive_And_Attacked() {
        dummy.takeAttack(ATTACK);
        assertEquals(HEALTH - ATTACK, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_Dummy_CannotBeAttacked_WhenDead() {
        deadDummy.takeAttack(ATTACK);
    }

    @Test
    public void test_Dummy_GivesExperience_WhenDead() {
        assertEquals(EXPERIENCE, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void test_Dummy_ThrowsException_WhenAlive() {
        assertEquals(EXPERIENCE, dummy.giveExperience());
    }

    @Test
    public void test_Dummy_IsDeadWhenHealth_IsZero() {
        assertTrue(deadDummy.isDead());
    }

    @Test
    public void test_Dummy_IsAliveWhenHealthIsMoreThanZero() {
        assertFalse(new Dummy(1, 1).isDead());
    }
}