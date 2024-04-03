package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTests {
    private Service service;
    private Robot robot;

    @Before
    public void setUp() {
        this.service = new Service("test1", 1);
        this.robot = new Robot("Robot1");
        this.service.add(robot);
    }


    @Test
    public void test_GetName() {
        Assert.assertEquals("test1", this.service.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_Should_Throw_When_Null() {
        Service service2 = new Service(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_Should_Throw_When_Empty() {
        Service service2 = new Service("", 1);
    }

    @Test
    public void test_GetCapacity() {
        Assert.assertEquals(1, this.service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Set_Capacity_Should_Throw_When_Negative() {
        Service service2 = new Service("test2", -1);
    }

    @Test
    public void test_Get_Count() {
        Assert.assertEquals(1, this.service.getCount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Add_Over_Capacity() {
        Robot robot2 = new Robot("Robot2");
        this.service.add(robot2);
    }

    @Test
    public void test_Remove() {
        this.service.remove("Robot1");
        Assert.assertEquals(0, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_Should_Throw() {
        this.service.remove("Robot2");
        Assert.assertEquals(0, this.service.getCount());
    }

    @Test
    public void test_ForSale() {
        this.service.forSale("Robot1");
        Assert.assertFalse(this.robot.isReadyForSale());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSale_Should_Throw() {
        this.service.forSale("Robot2");
        Assert.assertFalse(this.robot.isReadyForSale());
    }

    @Test
    public void test_Report() {
        Assert.assertEquals(
                "The robot Robot1 is in the service test1!", this.service.report()
        );
    }
}
