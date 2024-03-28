package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GarageTests {
    private Garage garage;
    private Car opel;
    private Car ford;
    private Car honda;

    @Before
    public void setUp(){
        this.garage = new Garage();
        opel = new Car("Opel",180, 3000);
        ford = new Car("Ford",160, 2800);
        honda = new Car("Honda",240, 4500);
    }

    @Test
    public void test_GetCount_ShouldReturnCorrectSize(){
        garage.addCar(opel);
        garage.addCar(ford);
        Assert.assertEquals(2,garage.getCount());
    }

    @Test
    public void test_GetCars_ShouldReturnCorrectCar(){
        garage.addCar(opel);
        garage.addCar(ford);
        Assert.assertEquals(opel.getBrand(),garage.getCars().get(0).getBrand());
    }

    @Test
    public void test_MaxSpeed_ShouldReturnCorrectCars(){
        garage.addCar(opel);
        garage.addCar(ford);
        Assert.assertEquals(opel.getBrand(),garage.findAllCarsWithMaxSpeedAbove(170).get(0).getBrand());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCar_ShouldThrow(){
        garage.addCar(null);
    }

    @Test
    public void test_Most_Expensive_Method(){
        garage.addCar(opel);
        garage.addCar(ford);
        garage.addCar(honda);
        Assert.assertEquals(honda.getBrand(),garage.getTheMostExpensiveCar().getBrand() );
    }

    @Test
    public void test_FindAllByBrand(){
        garage.addCar(opel);
        garage.addCar(opel);
        Assert.assertEquals(2,garage.findAllCarsByBrand("Opel").size() );
    }


}