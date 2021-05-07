import Formulas.*;
import Solvers.Bisection;
import Solvers.Secant;
import Solvers.Solver;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.dataset.DataSet;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class App {
    public static void main(String[] args) {
        Formula formula;
        Solver solver;
        double begin;
        double end;

        System.out.println("Marcin Gadziński 229877\nMichał Tosik 230027\n");
        System.out.println("Wybierz funkcję:");
        Formula[] examples = {new Polynomial(),
                new Exponential(), new Composition(), new Trigonometric()};
        for (int i = 0; i < examples.length; i++) {
            System.out.println(i + " - " + examples[i].getDescription());
        }
        Scanner sc = new Scanner(System.in);
        Integer choose = sc.nextInt();
        if (choose >= examples.length) {
            System.out.println("Zła liczba!");
            return;
        }
        formula = examples[choose];

        System.out.println("[1] - metoda bisekcji\n[2] - metoda siecznych");
        choose = 0;
        choose = sc.nextInt();
        solver = choose == 1 ? new Bisection() : new Secant();

        System.out.println("Podaj najpierw początek przedziału do poszukiwania, następnie koniec");
        begin = sc.nextDouble();
        end = sc.nextDouble();

        System.out.println("[1] - podaj dopuszczalny błąd\n[2] - podaj liczbę iteracji");
        Integer stopCondition = sc.nextInt();
        double res;
        int iterations = Integer.MAX_VALUE;
        double eps = -1;
        if (stopCondition == 1) {
            System.out.println("Podaj błąd");
            eps = sc.nextDouble();
        } else if (stopCondition == 2) {
            System.out.println("Podaj liczbę iteracji");
            iterations = sc.nextInt();
        } else {
            sc.close();
            return;
        }
        try {
            res = solver.calc(formula, begin, end, eps, iterations);
            System.out.println("Wynik to: " + Double.doubleToRawLongBits(res));
            System.out.println("Wynik to: " + Long.toBinaryString(Double.doubleToRawLongBits(res)));
        } catch (RuntimeException ex) {
            System.out.println("Podano zły przedział");
            sc.close();
            return;
        }
        sc.close();

        //Obliczenia do wykresu
        double range = end - begin;
        double step = range / 60; //na wykresie np 60 wartości
        double[] x = DoubleStream.iterate(begin, num -> num <= end, num -> num + step).toArray();
        double[][] data = new double[x.length][2];
        for (int i = 0; i < x.length; i++) {
            data[i][0] = x[i];
            data[i][1] = formula.getValue(x[i]);
        }

        //Wykres
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        DataSetPlot s = new DataSetPlot(data);
        myPlotStyle.setLineWidth(1);
        s.setPlotStyle(myPlotStyle);
        s.setTitle(formula.getDescription());
        p.addPlot(s);
        //Otrzymane rozwiązanie
        double[][] result = new double[][]{{res, 0.0}};
        DataSetPlot resPlot = new DataSetPlot(result);
        resPlot.setTitle("Wynik programu");
        p.addPlot(resPlot);
        p.setKey(JavaPlot.Key.OUTSIDE);
        p.set("zeroaxis", "");
        p.set("xzeroaxis", "");
        p.plot();
    }
}
