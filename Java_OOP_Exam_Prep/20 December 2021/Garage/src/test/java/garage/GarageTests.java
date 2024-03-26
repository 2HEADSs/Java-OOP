package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car porsche;
    private Car mercedes;
    private Car audi;

    @Before
    public void setUp() {
        this.garage = new Garage();
        porsche = new Car("Porsche", 300, 3000.23);
        mercedes = new Car("Mercedes", 200, 2000.23);
        audi = new Car("Audi", 220, 1000.23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCar_Should_Throw() {
        garage.addCar(null);
    }

    @Test
    public void test_Add_GarageSuccessfully() {
        garage.addCar(porsche);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(mercedes);
        Assert.assertEquals(2, garage.getCount());
    }

    @Test
    public void test_GetCars_Successfully() {
        garage.addCar(porsche);
        List<Car> carsInGarage= garage.getCars();
        Assert.assertEquals(1,garage.getCount());
        Assert.assertEquals(porsche.getBrand(), carsInGarage.get(0).getBrand());
    }

    @Test
    public void test_GetFastestCar() {
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        List<Car> carsWithSpeedAboveValue= garage.findAllCarsWithMaxSpeedAbove(200);

        Assert.assertEquals(porsche.getBrand(), carsWithSpeedAboveValue.get(0).getBrand());
    }

    @Test
    public void test_GetTheMostExpensiveCar(){
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        Car moostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(porsche.getBrand(), moostExpensiveCar.getBrand());
    }

    @Test
    public void test_findAllCarsByBrand(){
        garage.addCar(porsche);
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        List<Car> cars= garage.findAllCarsByBrand(porsche.getBrand());
        Assert.assertEquals(2, cars.size());

    }
}