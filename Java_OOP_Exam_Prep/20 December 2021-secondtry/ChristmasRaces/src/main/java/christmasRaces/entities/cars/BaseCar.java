package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car{
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model ==null ||model.trim().length() <4){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_MODEL, model,4)
            );
        }
        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        this.checkHorsePower(horsePower);
        this.horsePower = horsePower;
    }
    protected abstract void checkHorsePower(int horsePower);


    public void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters/this.horsePower * laps;
    }
}
