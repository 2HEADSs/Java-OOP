package wild_farm;

public abstract class Mammal extends Animal {


    protected Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    public boolean canEat(Food food) {
        return food.getClass().getSimpleName().equals("Vegetable");
    }
}
