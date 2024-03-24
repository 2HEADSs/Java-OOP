package cats;

import org.junit.Assert;
import org.junit.Test;


public class HouseTests {
    //1. House creating
//1.1 Invalid capacity
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouse_WithInvalidCapacity() {
        new House("House1", -5);
    }

    //1.2 Invalid name/null
    @Test(expected = NullPointerException.class)
    public void testCreateHouse_WithInvalidName() {
        new House(null, 23);
    }

    //1.2 Invalid name/empt
    @Test(expected = NullPointerException.class)
    public void testCreateHouse_WithEmptyName() {
        new House(" ", 23);
    }

    //1.3 Valid
    @Test
    public void testCreateHouse_WithValidInput() {
        House house = new House("House2", 11);
        Assert.assertEquals("House2", house.getName());
        Assert.assertEquals(11, house.getCapacity());
    }

    //2. addCat
    //2.1. Successfully added cat

    @Test
    public void testAddCat(){
        House house = new House("House2", 11);
        Cat mike = new Cat("Mike");
        Assert.assertEquals(0, house.getCount());
        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());

    }
    //2.1. Added cat in full house/ Unsuccessfully adding cat.

    @Test(expected =IllegalArgumentException.class )
    public void testAddCatThrowException(){
        House house = new House("House2", 1);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat bety = new Cat("Bety");
        house.addCat(bety);
    }

    //3.Remove
    //3.1 successfully removing cat:
    @Test
    public void testRemoveCat(){
        House house = new House("House2", 10);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat bety = new Cat("Bety");
        house.addCat(bety);
        Assert.assertEquals(2, house.getCount());
        house.removeCat("Bety");
        Assert.assertEquals(1, house.getCount());
        house.removeCat("Mike");
        Assert.assertEquals(0, house.getCount());
    }
    //3.2 there is no such cat:

    @Test(expected = IllegalArgumentException.class)
    public void testRemove_UnExisting_Cat(){
        House house = new House("House2", 10);
        house.removeCat("Mike");
    }

    //4. cat for sale
    //4.1.successfully sell cat:

    @Test
    public void test_CatForSale(){
        House house = new House("House2", 10);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat returnedCat =  house.catForSale("Mike");
        Assert.assertFalse(returnedCat.isHungry());
    }
    //4.2.unsuccessfully sell cat:
    @Test(expected = IllegalArgumentException.class)
    public void test_CatForSale_Throw(){
        House house = new House("House2", 10);
        house.catForSale("Stefan");
    }

    //5.Statistics
    @Test
    public void testStatistic(){
        House house = new House("House2", 10);
        Cat mike = new Cat("Mike");
        Cat bety = new Cat("Bety");
        Cat lora = new Cat("Lora");
        house.addCat(mike);
        house.addCat(bety);
        house.addCat(lora);
        Assert.assertEquals("The cat Mike, Bety, Lora is in the house House2!", house.statistics());

    }
}
