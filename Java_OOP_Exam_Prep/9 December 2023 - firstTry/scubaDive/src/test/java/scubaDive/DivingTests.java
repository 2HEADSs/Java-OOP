package scubaDive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class DivingTests {
    private Diving diving;
    private DeepWaterDiver deepWaterDiver1;
    private DeepWaterDiver deepWaterDiver2;
    private DeepWaterDiver deepWaterDiver3;

    @Before
    public void setUp() {
        this.diving = new Diving("DivingClass",3);
        deepWaterDiver1 = new DeepWaterDiver("Pavel",300);
        deepWaterDiver2 = new DeepWaterDiver("Stefan",150);
        deepWaterDiver3 = new DeepWaterDiver("Ivan",100);
    }


    @Test
    public void test_Get_Capacity(){
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        diving.addDeepWaterDiver(deepWaterDiver3);
        Assert.assertEquals(3,diving.getCount());
    }

    @Test
    public void test_Get_Name(){
        Assert.assertEquals("DivingClass",diving.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Diver(){
        diving.addDeepWaterDiver(deepWaterDiver2);
        diving.addDeepWaterDiver(deepWaterDiver2);
        Assert.assertEquals("DivingClass",diving.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Diver_Throw_WenCapacityIsNotEnough(){
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        diving.addDeepWaterDiver(deepWaterDiver3);
        diving.addDeepWaterDiver(new DeepWaterDiver("pshooo",2300));
        Assert.assertEquals("DivingClass",diving.getName());
    }
    @Test
    public void test_Remove_Diver_Return_True(){
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        Assert.assertTrue(diving.removeDeepWaterDiver("Pavel"));
    }
    @Test
    public void test_Remove_Diver_Return_False(){
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        Assert.assertFalse(diving.removeDeepWaterDiver("Pavel2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Set_Capacity_Should_Throw(){
        Diving diving2 = new Diving("test",-4);
    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Should_Throw(){
        Diving diving2 = new Diving("",555);
    }

}
