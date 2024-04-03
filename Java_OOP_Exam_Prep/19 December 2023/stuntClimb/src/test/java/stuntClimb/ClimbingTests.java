package stuntClimb;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClimbingTests {

    private Climbing climbing;
    private RockClimber rockClimber;

    @Before
    public void setUp() {
        this.climbing = new Climbing("test1", 1);
        this.rockClimber = new RockClimber("rock1", 23);
        climbing.addRockClimber(rockClimber);
    }

    @Test
    public void test_GetCount() {
        Assert.assertEquals(1, climbing.getCount());
    }

    @Test
    public void test_GetName() {
        Assert.assertEquals("test1", climbing.getName());
    }

    @Test
    public void test_GetCapacity() {
        Assert.assertEquals(1, climbing.getCapacity());
    }

    @Test
    public void test_AddRockClimber() {
        Climbing climbing2 = new Climbing("test2", 1);
        RockClimber rockClimber2 = new RockClimber("rock2", 10);
        climbing2.addRockClimber(rockClimber2);
        Assert.assertEquals(1, climbing2.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddRockClimber_Should_Throw_When_Capacity_Is_Reached() {
        RockClimber rockClimber2 = new RockClimber("rock2", 10);
        this.climbing.addRockClimber(rockClimber2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddRockClimber_Should_Throw_When_Climber_Exist() {
        this.climbing.addRockClimber(this.rockClimber);
    }

    @Test
    public void test_RemoveRockClimber_Return_True() {
        Assert.assertTrue(this.climbing.removeRockClimber(this.rockClimber.getName()));
    }

    @Test
    public void test_RemoveRockClimber_Return_False() {
        RockClimber rockClimber2 = new RockClimber("rock2", 10);
        Assert.assertFalse(this.climbing.removeRockClimber("rock2"));
    }

    @Test
    public void test_Set_Capacity(){
        Climbing climbing2 = new Climbing("test2", 1);
        Assert.assertEquals(1, climbing2.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Set_Capacity_Should_Throw_With_NegativeValue(){
        Climbing climbing2 = new Climbing("test2", -21);
    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Should_Throw_With_Empty(){
        Climbing climbing2 = new Climbing("", 1);
    }
    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Should_Throw_With_Null(){
        Climbing climbing2 = new Climbing(null, 1);
    }

}
