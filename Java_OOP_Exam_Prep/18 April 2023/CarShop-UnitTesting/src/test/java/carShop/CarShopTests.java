package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarShopTests {
    private CarShop carShop;
    private Car car;

    @Before
    public void setUp() {
        this.carShop = new CarShop();
        this.car = new Car("Ford", 200, 2000);
        this.carShop.add(car);
    }

    @Test
    public void test_GetCars(){
        Assert.assertEquals(1, this.carShop.getCars().size());
    }

    @Test
    public void test_GetCount(){
        Assert.assertEquals(1, this.carShop.getCount());
    }

    @Test
    public void test_FindAllMaxHP_EMmpty(){
        Assert.assertEquals(0, this.carShop.findAllCarsWithMaxHorsePower(200).size());
    }
    @Test
    public void test_FindAllMaxHP(){
        Assert.assertEquals(1, this.carShop.findAllCarsWithMaxHorsePower(100).size());
    }

    @Test
    public void test_Add_Car(){
        Car car1 = new Car("Opel",100,2);
        this.carShop.add(car1);
        Assert.assertEquals(2, this.carShop.getCount());
    }


    @Test(expected = NullPointerException.class)
    public void test_Add_Null_Car(){
        this.carShop.add(null);
    }

    @Test
    public void test_Remove_True(){
        Assert.assertTrue(this.carShop.remove(car));
    }

    @Test
    public void test_Remove_False(){
        Car car1 = new Car("Opel",100,2);
        Assert.assertFalse(this.carShop.remove(car1));
    }

    @Test
    public void test_MostLuxury(){
        Car car1 = new Car("Opel",100,2);
        this.carShop.add(car1);
        Assert.assertEquals(car,this.carShop.getTheMostLuxuryCar());
    }

    @Test
    public void test_FindByModel(){
        List<Car> cars = new ArrayList<>();
        cars.add(this.car);
        Assert.assertEquals(cars, this.carShop.findAllCarByModel("Ford"));
    }

}

