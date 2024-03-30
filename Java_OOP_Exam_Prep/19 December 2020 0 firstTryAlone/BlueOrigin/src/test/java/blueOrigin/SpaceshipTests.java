package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    Spaceship spaceship;
    Spaceship spaceship2;
    private Astronaut first;
    private Astronaut second;
    private Astronaut third;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("Flakon", 1);
        this.spaceship2 = new Spaceship("Flakon2", 22);
        this.first = new Astronaut("Pesho", 100);
        this.second = new Astronaut("Ivan", 75);
        this.third = new Astronaut("Stefan", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityWithNegativeNumber() {
        Spaceship spaceship = new Spaceship("Flakon", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testNameWithNull() {
        Spaceship spaceship = new Spaceship(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void withEmptyString() {
        Spaceship spaceship = new Spaceship("", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailWithoutCapacity() {
        spaceship.add(first);
        spaceship.add(second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailWithRepeatableAstronauts() {
        spaceship.add(first);
        spaceship.add(first);
    }

    @Test
    public void testAddShouldWork() {
        spaceship2.add(first);
        spaceship2.add(second);
        Assert.assertEquals(2,spaceship2.getCount());

    }

    @Test
    public void testCapacity() {
        spaceship.add(first);
        Assert.assertEquals(1,spaceship.getCapacity());
    }

    @Test
    public void testName() {
        spaceship.add(first);
        Assert.assertEquals("Flakon",spaceship.getName());
    }

    @Test
    public void testRemoveShouldReturnTrue() {
        spaceship.add(first);
        boolean result =  spaceship.remove("Pesho");
        Assert.assertTrue(result);
    }
    @Test
    public void testRemoveShouldReturnFalse() {
        spaceship.add(first);
        boolean result =  spaceship.remove("baba");
        Assert.assertFalse(result);
    }
}
