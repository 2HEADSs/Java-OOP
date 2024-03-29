package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    private static final int LONGHAIR_CAT_KG = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price, LONGHAIR_CAT_KG);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 3);
    }
}
