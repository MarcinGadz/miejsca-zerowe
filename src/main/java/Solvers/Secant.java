package Solvers;

import Formulas.Calculator;
import Formulas.Formula;

import static java.lang.Math.abs;

public class Secant implements Solver {
    private boolean check(Formula formula, double begin, double end) {
        if (formula.getValue(begin) * formula.getValue(end) > 0) {
            return false;
        }
        return true;
    }

    public double calc(Formula formula, double begin, double end, double eps, int iterations) {
        if(!check(formula, begin, end)) {
            throw new RuntimeException("Bad numbers");
        }
        if (formula.getValue(end) == 0.0) {
            return end;
        }
        if (formula.getValue(begin) == 0.0) {
            return begin;
        }
        int currentIterations = 0;
        double x3;
        do {
            currentIterations++;
            x3 = (formula.getValue(end) * begin - formula.getValue(begin) * end) /
                    (formula.getValue(end) - formula.getValue(begin));

            if (formula.getValue(x3) == 0.0) {
                System.out.println("Wykonane iteracje: " + currentIterations);
                return x3;
            }

            begin = end;
            end = x3;

        } while(abs(x3 - begin) > eps && currentIterations < iterations);
        System.out.println("Wykonane iteracje: " + currentIterations);
        return x3;
    }
}
