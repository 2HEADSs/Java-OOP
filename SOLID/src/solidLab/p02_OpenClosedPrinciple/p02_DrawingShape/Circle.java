package solidLab.p02_OpenClosedPrinciple.p02_DrawingShape;

import solidLab.p02_OpenClosedPrinciple.p02_DrawingShape.interfaces.Shape;

public class Circle implements Shape {
    @Override
    public double getArea() {
        return Math.PI + 2;
    }
}
