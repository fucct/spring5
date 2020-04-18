package spring5.domain.chap07;

public class IterCalculator implements Calculator {

    @Override
    public long factorial(final long num) {
        long result = 1;

        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}