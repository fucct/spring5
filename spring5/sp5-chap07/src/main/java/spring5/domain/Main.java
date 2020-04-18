package spring5.domain;

import spring5.domain.chap07.Calculator;
import spring5.domain.chap07.IterCalculator;
import spring5.domain.chap07.RecCalculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new IterCalculator();
        Calculator calculator1 = new RecCalculator();
        Calculator calculator2 = new ExeTimeCalculator(calculator);
        Calculator calculator3 = new ExeTimeCalculator(calculator1);

        calculator2.factorial(50);
        calculator3.factorial(50);
    }
}
