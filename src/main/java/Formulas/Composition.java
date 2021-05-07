package Formulas;

public class Composition implements Formula {
    @Override
    public String getDescription() {
        return "sin(3x^2) + x^3 - 1";
    }

    @Override
    public double getValue(double x) {
        return Math.sin(3 * x * x) + x * x * x - 1;
    }
}
