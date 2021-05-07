package Solvers;

import Formulas.Formula;

public interface Solver {
    double calc(Formula formula, double begin, double end, double eps, int iterations);
}
