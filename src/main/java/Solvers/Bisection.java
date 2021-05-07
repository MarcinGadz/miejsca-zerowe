package Solvers;

import Formulas.Formula;

import static java.lang.Math.abs;

public class Bisection implements Solver {
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
        if (formula.getValue(begin) == 0.0) {
            return begin;
        }
        if (formula.getValue(end) == 0.0) {
            return end;
        }
        double middle = begin;
        double temp;
        int currentIterations = 0;
        do {
            currentIterations++;
            temp = middle;
            middle = (begin + end) / 2;
            if (formula.getValue(middle) == 0.0) {
                System.out.println("Wykonane iteracje: " + currentIterations);
                return middle;
            }
            if (formula.getValue(begin) *
                    formula.getValue(middle) < 0) {
                end = middle;
            } else {
                begin = middle;
            }

        } while(abs(temp - middle) > eps && currentIterations < iterations);
        System.out.println("Wykonane iteracje: " + currentIterations);
        return middle;
    }
}
