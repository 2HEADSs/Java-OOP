package wild_farm;

public class Mouse extends Mammal {
    public Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eatFood(Food food) {
        if(!canEat(food)){
            System.out.println("Mice are not eating that type of food!");
        }else {
            super.eatFood(food);
        }
    }
}
