package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalStateException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        House house = getHouse(houseName);
        house.buyToy(toy);
        this.toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType){
            case "ShorthairCat":
                cat = new ShorthairCat(catName,catBreed,price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName,catBreed,price);
                break;
            default:
                throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_CAT_TYPE));
        }
        House house = getHouse(houseName);
        boolean checkShort = house.getClass().getSimpleName().startsWith("Short") && catType.startsWith("Short");
        boolean checkLong = house.getClass().getSimpleName().startsWith("Long") && catType.startsWith("Long");
        if(checkShort || checkLong){
            house.addCat(cat);
        }else {
            return String.format(ConstantMessages.UNSUITABLE_HOUSE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType,houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouse(houseName);
        house.feeding();


        return String.format(ConstantMessages.FEEDING_CAT,house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {

        House house = getHouse(houseName);
        double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double totalPrice = catsPrice + toysPrice;
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, totalPrice);
    }

    @Override
    public String getStatistics() {
        return null;
    }
    private House getHouse(String houseName) {
        return this.houses.stream().filter(h -> h.getName().equals(houseName))
                .findFirst().get();
    }
}
