package Formulas;

public class Exponential implements Formula {
    @Override
    public String getDescription() {
        return "3^x - 1";
    }

    @Override
    public double getValue(double x) {
        if (x == (int) x) {
            //jeśli tak naprawdę podano liczbę całkowitą
            double res = 3;
            int integerX = (int) x;
            for (int i = 1; i < integerX; i++) {
                res *= 3;
            }
            if(x < 0) {
                //Gdy wykładnik ujemny x^-n = 1/x^n
                res = 1/res;
            }
            return res - 1;
        }
        return Math.pow(3, x) - 1;
    }
}
