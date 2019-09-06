package be.webtechie.javafxlednumberdisplay.definition;

public class LineDefinition {

    private final String label;

    private final double[] pointsX;
    private final double[] pointsY;

    public LineDefinition(String label, double[] pointsX, double[] pointsY) {
        this.label = label;
        this.pointsX = pointsX;
        this.pointsY = pointsY;

        if (this.pointsX.length != this.pointsY.length) {
            throw new IllegalArgumentException("Both points arrays need to have the same number of elements");
        }
    }

    public String getLabel() {
        return label;
    }

    public double[] getPointsX() {
        return pointsX;
    }

    public double[] getPointsY() {
        return pointsY;
    }
}
