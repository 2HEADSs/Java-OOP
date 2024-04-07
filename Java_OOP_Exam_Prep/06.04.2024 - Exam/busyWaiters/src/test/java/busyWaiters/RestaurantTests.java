package busyWaiters;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class RestaurantTests {
    private Restaurant restaurant;
    private FullTimeWaiter fullTimeWaiter;

    @Before
    public void setUp() {
        this.restaurant = new Restaurant("test1", 2);
        this.fullTimeWaiter = new FullTimeWaiter("waiter1", 23);
        this.restaurant.addFullTimeWaiter(fullTimeWaiter);
    }

    @Test
    public void test_Get_Capacity() {
        Assert.assertEquals(2, this.restaurant.getCapacity());
    }

    @Test
    public void test_GetName(){
        Assert.assertEquals("test1",this.restaurant.getName());
    }

    @Test
    public void test_Get_Waiter(){
        Collection<FullTimeWaiter> fullTimeWaiters1 = new ArrayList<>();
        fullTimeWaiters1.add(this.fullTimeWaiter);
        Assert.assertEquals(fullTimeWaiters1,this.restaurant.getWaiters());
    }

    @Test
    public void test_Get_Count(){
        Assert.assertEquals(1,this.restaurant.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Should_Throw_If_Full(){
        FullTimeWaiter fullTimeWaiter1 = new FullTimeWaiter("waiter2", 23);
        FullTimeWaiter fullTimeWaiter2 = new FullTimeWaiter("waiter3", 23);
        this.restaurant.addFullTimeWaiter(fullTimeWaiter1);
        this.restaurant.addFullTimeWaiter(fullTimeWaiter2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Should_Throw_Duplicate_Waiter(){
        FullTimeWaiter fullTimeWaiter1 = new FullTimeWaiter("waiter1", 23);
        this.restaurant.addFullTimeWaiter(fullTimeWaiter1);
    }

    @Test
    public void test_Add_Work(){
        FullTimeWaiter fullTimeWaiter1 = new FullTimeWaiter("waiter2", 23);
        this.restaurant.addFullTimeWaiter(fullTimeWaiter1);
        Assert.assertEquals(2,this.restaurant.getCount());
    }

    @Test
    public void test_Remove(){
        Assert.assertTrue(this.restaurant.removeFullTimeWaiter(this.fullTimeWaiter.getName()));
    }

    @Test
    public void test_Remove_Not_Existing(){
        Assert.assertFalse(this.restaurant.removeFullTimeWaiter("psho"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Set_Invalid_Capacity(){
        Restaurant restaurant1 = new Restaurant("test2",-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Invalid_Name(){
        Restaurant restaurant1 = new Restaurant("",12);
    }
}
