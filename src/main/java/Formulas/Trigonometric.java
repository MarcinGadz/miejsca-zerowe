package Formulas;

public class Trigonometric implements Formula {
    @Override
    public String getDescription() {
        return "sin(0,75x)";
    }

    @Override
    public double getValue(double x) {
        return Math.sin(0.75 * x);
    }
}
