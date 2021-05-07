package Formulas;

public class Polynomial implements Formula {
    @Override
    public String getDescription() {
        return "0,5x^3 + x^2 + 1";
    }

    @Override
    public double getValue(double x) {
        double[] coeffs = {0.5, 1, 0, 1};
        int len = 4;
        return Calculator.horner(coeffs, len, x);
    }
}
